package example.service;

import model.dto.Card;
import model.dto.Item;
import model.dto.JokboDto;
import model.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Random;

public class ItemUseService {
        // 1. DB에서 아이템 번호 가져오기
        private ItemUseService() { }
        private static final ItemUseService instance = new ItemUseService();
        public static ItemUseService getInstance() { return instance; }


        private PlayerDto player = PlayerDto.getInstance();
        private GameService gameService = GameService.getInstance();

        private Card buffedCard = null;
        private String originalType = "";
        private String originalName = "";
        public int getItemCount(int itemId) {
            if (player.getItem() == null) return 0;
            int count = 0;
            for (Item item : player.getItem()) {
                if (item.getItem_no() == itemId) {
                    count++;
                }
            }
            return count;
        }

        //[공통] 유저가 특정 번호의 아이템을 가지고 있는지 확인하는 메서드
        public boolean hasItem(int itemId) {
                if (player.getItem() == null) return false;
                for (Item item : player.getItem()) {
                        if (item.getItem_no() == itemId) {
                                return true;
                        }
                }
                return false;
        }

        public boolean useRevelationOfSpirit() {
                ArrayList<Card> hand = gameService.getCurrent_hand();

                // 현재 손패에서 '광'이 아닌 카드만 추려냄
                ArrayList<Card> nonKwangCards = new ArrayList<>();
                for (Card c : hand) {
                        if (!c.getType().equals("광")) {
                                nonKwangCards.add(c);
                        }
                }

                // 광이 아닌 카드가 하나도 없다면? (이미 다 광이거나 패가 없음)
                if (nonKwangCards.isEmpty()) {
                        System.out.println("⚠️ 손패에 변환할 수 있는 카드(광이 아닌 카드)가 없습니다!");
                        return false; // 사용 취소 (아이템 소모 안 함)
                }

                // 2. 광이 아닌 카드 중에서 랜덤하게 1장 뽑기
                Random rand = new Random();
                Card targetCard = nonKwangCards.get(rand.nextInt(nonKwangCards.size()));

                // 3. 원래 타입을 기억해두고 '광'으로 임시 변환
                buffedCard = targetCard;
                originalType = targetCard.getType();
                originalName = targetCard.getName();;
                targetCard.setType("광");
                targetCard.setName(targetCard.getMonth() + "월_광");
                System.out.println("\n✨ [신령님의 계시 발동!] 손패의 카드가 찬란하게 빛납니다...");
                System.out.println("👉 이번 턴에만 [" + targetCard.getMonth() + "월 " + originalType + "] 카드가 [광]으로 취급됩니다!");
                consumeItem(4);
                return true;
        }

        public void revertCardIfNeeded(ArrayList<Card> movedCards) {
                if (buffedCard == null) return; // 변환했던 카드가 없으면 패스

                // 제출하거나 버린 카드 목록(movedCards)에 내가 변환했던 카드가 들어있다면?
                if (movedCards.contains(buffedCard)) {
                        buffedCard.setType(originalType); // 원래 타입(피, 띠, 열)으로 원상복구!
                        buffedCard.setName(originalName);
                        buffedCard = null; // 기억 지우기
                        originalType = "";
                        originalName = "";
                }
        }

        public void clearBuff() {
                if (buffedCard != null) {
                        buffedCard.setType(originalType);
                        buffedCard.setName(originalName);
                        buffedCard = null;

                        originalType = "";
                        originalName = "";
                }
                ancestorBuffActive = false;
                itemstate = false;
        }

        public boolean useBottomDealing() {
                ArrayList<Card> hand = gameService.getCurrent_hand();
                if (hand.isEmpty()) {
                        System.out.println("⚠️ 버릴 손패가 없습니다!");
                        return false;
                }

                int handSize = hand.size();

                // 손패를 모두 무덤으로
                gameService.getCurrent_grave().addAll(hand);
                hand.clear();

                // 버린 만큼 새로 덱에서 뽑기
                gameService.drawCard(handSize);

                System.out.println("\n🃏 [밑장 빼기 발동!] 손패 " + handSize + "장을 모두 버리고 은밀하게 새로 뽑았습니다!");
                consumeItem(5);
                return true;
        }

        public int getAnimalBoostScore(ArrayList<Card> submittedCards) {
            int count = getItemCount(1); // 호랑이 기운이 몇 개인지 확인!
            if (count == 0) return 0;

            int bonus = 0;
            for (Card card : submittedCards) {
                if (card.getType().equals("열")) {
                    bonus += (10 * count);
                }
            }
            if (bonus > 0) {
                System.out.println("🐯 [호랑이 기운 발동] 으르렁! (x" + count + "중첩) '열' 카드 보너스 +" + bonus + "점!");
            }
            return bonus;
        }

        public int applyWealthAmulet(int totalMoney) {
            int count = getItemCount(2); // 재물 부적이 몇 개인지 확인
            if (count == 0) return totalMoney;

            double multiplier = 1.0 + (0.5 * count);
            int boostedMoney = (int) (totalMoney * multiplier);
            System.out.println("💸 [재물 부적 발동] 엽전이 복사됩니다! (x" + count + "중첩) 획득 금액 " + multiplier + "배 증가!");
            return boostedMoney;
        }

        public int getKwangMultiplier() {
            int count = getItemCount(3); // 광끼 부적이 몇 개인지 확인
            if (count > 0) {

                return 1 + count;
            }
            return 1;
        }
        //==============================================

    // 1회용 아이템 사용시 player가 보유한 리스트에서 삭제(6번, 10번)
    private boolean consumeItem(int itemId) {
        if (player.getItem() == null) return false;

        for (int i = 0; i < player.getItem().size(); i++) {  // 플레이어가 가지고있는 아이템을 확인
            if (player.getItem().get(i).getItem_no() == itemId) {   // 플레이어가 가지고 있는 아이템의 번호와 해당 아이템 번호가 일치하면 삭제
                player.getItem().remove(i);
                return true;
            }
        }
        return false;
    }

    // 아이템 번호 6 (조상님의 도움)(점괘)  다음 족보 배수를 +3배 추가한다
    private boolean ancestorBuffActive = false;
    public boolean ancestorHelp(){

        if (!hasItem(6)) return false;  // 아이템 없으면 사용 불가

        ancestorBuffActive = true;           // 다음 족보에 적용
        consumeItem(6);               // player 보유 아이템 리스트에서 삭제

        System.out.println("[조상님의 도움 발동] 다음 족보 배수 +3");
        return true;
    }
    // 아이템 6번 발동 상태
    public int getAncestorMultiplier() {

        if (!ancestorBuffActive)
            return 0; // 아이템 발동중이 아니면 +0배

        ancestorBuffActive = false; // 한 번 적용 후 자동 해제
        return 3;
    }
    // 7번
    private boolean itemstate = false;

    // 아이템 번호 7 (동작 그만)(점괘)
    public boolean moveStop(){
        //지금 패를 다음 판에도 유지한다
        if (!hasItem(7)) return false;

        itemstate = true;
        consumeItem(7);
        System.out.println("[동작 그만 발동] 지금 패를 제출한 후에도 유지(제출 전까지 버리기 가능)");
        return true;
    }
    public  boolean getItemstate(){
        if(!itemstate){
            return false;
        }
        itemstate = false;
        return true;
    }


    // 아이템 번호 8 (붉은 띠)(부적)
    public int redBand(JokboDto jokbo){
        if (jokbo == null) return 0;
        int count = getItemCount(8); // 붉은 띠가 몇 개인지 확인
        if(count > 0 && jokbo.getJokboNo() == 10) {
            int multiplier = 3 * count;
            System.out.println("🧧 [붉은 띠 발동] (x" + count + "중첩) 홍단 점수가 +" + multiplier + "배 ");
            return multiplier;
        }
        return 0;
    }

    // 아이템 번호 9 (푸른 띠)(부적)
    public int blueBand(JokboDto jokbo) {
        if (jokbo == null) return 0;
        int count = getItemCount(9); // 푸른 띠가 몇 개인지 확인

        if(count > 0 && jokbo.getJokboNo() == 11) {
            int multiplier = 3 * count;
            System.out.println("🎐 [푸른 띠 발동] (x" + count + "중첩) 청단 점수가 +" + multiplier + "배 ");
            return multiplier;
        }
        return 0;
    }

    // 아이템 번호 10 (아수라발발타)(점괘)
    public boolean magic(){
        // 목숨을 3개로 만든다.
        if (!hasItem(10)){  // 아이템을 가지고 있지 않으면
           return false;
        }
        if(player.getCurrent_hp() >= 5){   // 현재 기회가 5개 이상이면 사용 불가
            System.out.printf("아이템 사용불가(현재 기회 : %d)\n",player.getCurrent_hp());
            return false;
        }
        player.setCurrent_hp(5); // 기회 : 5
        consumeItem(10); // 아이템 10번 삭제
        System.out.println("[아수라발발타 발동] 기회 : 5");
        return true;
    }
}


