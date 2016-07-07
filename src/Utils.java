
public class Utils {

	public static int max(int i, int j) {
		// TODO Auto-generated method stub
		return i > j ? i : j;
	}
	public static int min(int i, int j) {
		// TODO Auto-generated method stub
		return i < j ? i : j;
	}
	public static void printArray(int[] array){
		for(int i = 0; i< array.length; i++){
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
	public static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	public static void printObjArray(Integer[] array){
		for(int i = 0; i< array.length; i++){
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
