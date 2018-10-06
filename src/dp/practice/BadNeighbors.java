package dp.practice;

public class BadNeighbors {
    public boolean[] donated;
    public int[] donations;
    public int head;
    public boolean canHeadTailBothDonate;
    public int dp_donation(int left, int right){
        if(left > right){
            return 0;
        }
        int money1 = 0;  //left捐钱的场景
        int money2 = 0;  //left不捐的场景
        boolean canDonate = false;
        if(left == this.head){//第一位可以捐
            canDonate = true;
        }else if(left == this.donations.length){//最后一位
            if(this.canHeadTailBothDonate){
                // 只需要看他前一位
                if(!donated[left - 1]){
                    canDonate = true;
                }
            }else{
                // 除了前一位还要看第一位
                if(!donated[left-1] && !donated[0]){
                    canDonate = true;
                }
            }
        } else if(!donated[left-1]){//其余的人只要前一位没有捐就可以捐
            canDonate = true;
        }
        if(canDonate){//left可以捐
            donated[left] = true;
            money1 += this.donations[left];
            money1 += dp_donation(left + 1, right);
            donated[left] = false;
        }
        money2 = dp_donation(left+1, right);
        return money1 > money2 ? money1 : money2;
    }
    public int maxDonations(int[] donations){
        this.donations = donations;
        this.canHeadTailBothDonate = false;
        int left = 0;
        int maxDonation = 0;
        boolean firstTime = true;
        int firstSegmentEnd = 0;
        for(int i = 1; i < donations.length-1;){
            if((donations[i-1]+donations[i+1]) <= donations[i]){
                maxDonation += donations[i];
                //System.out.println(donations[i]);
                if(firstTime){//第一段留着，等结束了和最后一段拼在一起计算
                    this.canHeadTailBothDonate = true;
                    firstSegmentEnd = i - 2;
                }else{//不是第一段了就计算掉
                    this.head = left;
                    maxDonation += dp_donation(left, i - 2);
                }
                left = i + 2;
                i = i + 2;
            }else{
                i++;
            }
        }
        int[] seg = new int[(firstSegmentEnd + 1) + (this.donations.length - left + 1)];//第一段和最后一段拼在一起
        for(int i = 0; i <= this.donations.length - 1 - left; i++){
            seg[i] = donations[left + i];
        }
        for(int i = 0; i <= firstSegmentEnd; i++){
            seg[(this.donations.length - left) + i] = donations[i];
        }

        this.head = 0;
        this.canHeadTailBothDonate = true;
        this.donations = seg;
        maxDonation += dp_donation(0, this.donations.length - 1);;
        return maxDonation;
    }
    public BadNeighbors(){
        donated = new boolean[1010];
        for(int i = 0; i < 1010; i++){
            donated[i] = false;
        }
    }
}
