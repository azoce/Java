//iString型のキュー
public class StringQueue {
	private int max;	//キューの容量
	private int front;	//先頭要素のカーソル
	private int rear;	//末尾要素のカーソル
	private int num;	//現在のデータ数
	private String[] que;	//キューの本体

	//実行時キューが空
	public class EmptyStringQueueException extends RuntimeException{
		public EmptyStringQueueException() {}
	}

	//実行時キューが満杯
	public class OverflowStringQueueException extends RuntimeException{
		public OverflowStringQueueException() {}
	}

	//コンストラクタ
public StringQueue(int capacity) {
		num=front=rear=0;
		max=capacity;
		try {
			que=new String [max];	//キューの本体用の配列を生成
		}catch (OutOfMemoryError e) {	//生成できなかった
			max=0;
		}
	}

	//キューにデータをエンキュ―
	public String enque(String x) throws OverflowStringQueueException{
		if(num>=max)
			throw new OverflowStringQueueException();	//キューは満杯
		que[rear++]=x;
		num++;
		if(rear==max)
			rear=0;
		return x;
	}

	//キューからデータをデキュー
	public String deque() throws EmptyStringQueueException{
		if(num<=0)
			throw new EmptyStringQueueException();	//キューは空
		String x=que[front++];
		num--;
		if(front==max)
			front=0;
		return x;
	}

	//キューからデータをピーク
	public String peek() throws EmptyStringQueueException{
		if(num<=0)
			throw new EmptyStringQueueException();	//キューは空
		return que[front];
	}

	//キューからxを探してインデックスを返す
	//見つからなければ-1を返す
	public int indexOf(String x) {
		for(int i=0;i<num;i++) {
			int idx=(i+front)%max;	//%にすることでmax以上を0とか1とか2にできる。
			if(que[idx]==x)
				return idx;		//探索成功
		}
		return -1;	//探索失敗
	}

	//キュ-を空にする
	public void clear() {
		num=front=rear=0;
	}

	//キューの容量を返す
	public int capacity() {
		return max;
	}

	//キューに蓄えられているデータを数を返す
	public int size() {
		return num;
	}

	//キューは空であるか
	public boolean isEmpty() {
		return num<=0;
	}

	//キューは満杯であるか
	public boolean isFull() {
		return num>=max;
	}

	//キュー内の全データを先頭から末尾の順に表示
	public void dump() {
		if(num<=0)
			System.out.println("キューは空です。");
		else {
			for(int i=0;i<num;i++)
				System.out.print(que[(i+front)%max]+"");
			System.out.println();
		}
	}

	//今入っている数
		public int remainnum() {
			return (rear%max);
		}

}
