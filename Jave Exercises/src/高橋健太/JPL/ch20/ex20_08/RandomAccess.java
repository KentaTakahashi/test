package 高橋健太.JPL.ch20.ex20_08;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomAccess{

	RandomAccessFile raf;
	List<Long> entry = new ArrayList<Long>();

	public RandomAccess(String current_pass){

		try {
			raf = new RandomAccessFile(current_pass, "r");
			int r;
			boolean preEntry = false;//ひとつ前の文字が%か
			while((r = raf.read()) != -1) {
				if(r == '%' && preEntry)
					entry.add(raf.getFilePointer());
				else if(r == '%')
					preEntry = true;
				else
					preEntry = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getEntryLength() {
		return entry.size();
	}

	public void printEntry(int index) {
		try {
			long begin = entry.get(index);
			long end = index + 1 == entry.size() ? raf.length(): entry.get(index + 1) - 2;
			raf.seek(begin);
			while(raf.getFilePointer() < end)
				System.out.print((char)raf.read());
			System.out.print('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		current_pass = current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_08\\inFile.txt";
		RandomAccess ra = new RandomAccess(current_pass);

		int length = ra.getEntryLength();

		//Randomクラスのインスタンス化
        Random rnd = new Random();
        for(int i = 0; i < 10; i++) {
            int ran = rnd.nextInt(length);
        	System.out.print(ran + " entry is ");
    		ra.printEntry(ran);
        }
	}
}
