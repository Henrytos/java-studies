
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        int[] answer = new int[2];

        while (start < end) {

            if (nums[start] + nums[end] == target) {
                answer[0] = start;
                answer[0] = end;
                break;
            } else if (nums[start] + nums[end] > target) {
                end--;
            } else {
                start++;
            }

        }

        return answer;

        // for(int x = 0; x < nums.length; x++){
        // for(int y = nums.length - 1; y > x; y--){

        // if(nums[x] + nums[y] == target){
        // return new int[] {x,y} ;
        // }

        // }
        // }
        // return null;

    }
}

class InnerTwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = twoSum.twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }

}