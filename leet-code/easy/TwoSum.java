class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        Integer[] ordenado = new Integer[nums.length];
        Integer ultimoIndex = 0;
        while (ultimoIndex < nums.length) {
            Integer menorIndexEnumerado = menorIndexEnumerado(nums);

            Integer menorNumero = nums[menorIndexEnumerado];
            Integer indiceDoMenorNumero = menorIndexEnumerado;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0 && nums[i] < menorNumero) {
                    menorNumero = nums[i];
                    indiceDoMenorNumero = i;
                }
            }

            ordenado[ultimoIndex] = menorNumero;
            nums[indiceDoMenorNumero] = 0;
            ultimoIndex++;
        }

        Integer l = 0;
        Integer r = ordenado.length - 1;

        Integer[] resposta = new Integer[2];
        while (l < r) {
            if (ordenado[l] + ordenado[r] == alvo) {
                Integer indexOne = map.get(ordenado[l]);
                Integer indexTwo = map.get(ordenado[r]);

                Integer temp;

                if (indexOne > indexTwo) {
                    temp = indexOne;
                    indexOne = indexTwo;
                    indexTwo = temp;
                }
                resposta[0] = indexOne;
                resposta[1] = indexTwo;
                break;
            } else if (ordenado[l] + ordenado[r] > alvo) {
                r--;
            } else {
                l++;
            }
        }

        return resposta;

    }
}