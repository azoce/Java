//String型スタック
public class StringStack {

	private int max;	//スタックの容量
	private int ptr;	//スタックのポインタ
	private String[]stk;	//スタックの本体

	//実行時スタックが空
	public class EmptyStringStackException extends RuntimeException{
		public EmptyStringStackException() {}
	}

	//実行時スタックが満杯
	public class OverflowStringStackException extends RuntimeException{
		public OverflowStringStackException() {}
	}

	//コンストラクタ
	public StringStack(int capacity) {
		ptr=0;
		max=capacity;
		try {
			stk=new String[max];	//スタック本体用の配列を生成
		}catch(OutOfMemoryError e) {	//生成できなかった
			max=0;
		}
	}

	//スタックにxをプッシュ
	public String push(String x) throws OverflowStringStackException{
		if(ptr>=max)	//スタックは満杯
			throw new OverflowStringStackException();
		return stk[ptr++]=x;
	}

	//スタックからデータをポップ
	public String pop() throws EmptyStringStackException{
		if(ptr<=0)	//スタックは空
			throw new EmptyStringStackException();
		return stk[--ptr];
	}

	//スタックからデータをピーク
	public String peek() throws EmptyStringStackException{
		if(ptr<=0)	//スタックは空
			throw new EmptyStringStackException();
		return stk[ptr-1];
	}

	//スタックからxを探してインデックスを返す。
	//見つからなければ-1を返す
	public int indexOf(String x) {
		for (int i=ptr-1;i>=0;i--)	//頂上から線形探索
			if(stk[i]==x)
				return i;	//探索成功
		return -1;	//探索失敗
	}

	//スタックを空にする
	public void clear() {
		ptr=0;
	}

	//スタックの容量を返す
	public int capacity() {
		return max;
	}

	//スタックに積まれているデータ数を返す
	public int size() {
		return ptr;
	}

	//スタックは空であるか
	public boolean isEmpty() {
		return ptr<=0;
	}

	//スタックは満杯であるか
	public boolean isFull() {
		return ptr>=max;
	}

	//スタック内の全データを底から頂上の順に表示
	public void dump() {
		if(ptr<=0)
			System.out.println("スタックは空です。");
		else {
			for(int i=0;i<ptr;i++)
				System.out.print(stk[i]+"");
			System.out.println();
		}
	}
	//今入っている数
	public int remainnum() {
		return ptr;
	}
}
