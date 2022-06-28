
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class calculator extends Application {

	//計算結果ラベル生成

    TextField tfCalc = new TextField();

    //各種ボタン生成
    Button Zero=new Button("0");
    Button One=new Button("1");
    Button Two=new Button("2");
    Button Three=new Button("3");
    Button Four=new Button("4");
    Button Five=new Button("5");
    Button Six=new Button("6");
    Button Seven=new Button("7");
    Button Eight=new Button("8");
    Button Nine=new Button("9");
    Button Addition=new Button("+");
    Button Subtraction=new Button("－");
    Button Multiplication=new Button("×");
    Button Division=new Button("÷");
    Button Point=new Button(".");
    Button RBrackets=new Button("(");
    Button LBrackets=new Button(")");
    Button Clear=new Button("C");
    Button Answer=new Button("=");

    //ボタン整理
    VBox vb;


    //キーボード配置
    public void CreateKeyboard() {
    	//各種HBox定義と配置
    	HBox h1=new HBox(tfCalc);	//ラベル
    	tfCalc.setEditable(false);
    	
    	
    	h1.setAlignment(Pos.CENTER);	//h1を中央配置
    	HBox h2=new HBox(10,Zero,One,Two,Three,Four);	//0,1,2,3,4
    	h2.setAlignment(Pos.CENTER);
    	HBox h3=new HBox(10,Five,Six,Seven,Eight,Nine);	//5,6,7,8,9
    	h3.setAlignment(Pos.CENTER);
    	HBox h4=new HBox(10,Addition,Subtraction,Multiplication,Division);//+,-,×,÷
    	h4.setAlignment(Pos.CENTER);
    	HBox h5=new HBox(10,Point,RBrackets,LBrackets,Clear,Answer);//.,(,),C,=
    	h5.setAlignment(Pos.CENTER);

    	//VBoxでは位置決めしたものをVBoxに配置
    	vb=new VBox(10,h1,h2,h3,h4,h5);
    }


    //ボタンクラス

    public void btnClicked() {
    	//各種ボタン機能
    	//0
    	Zero.setOnAction(e->{
    		String str0=tfCalc.getText();
    		str0+="0";
    		tfCalc.setText(str0);
    	});
    	//1
    	One.setOnAction(e->{
    		String str1=tfCalc.getText();
    		str1+="1";
    		tfCalc.setText(str1);
    	});
    	//2
    	Two.setOnAction(e->{
    		String str2=tfCalc.getText();
    		str2+="2";
    		tfCalc.setText(str2);
    	});
    	//3
    	Three.setOnAction(e->{
    		String str3=tfCalc.getText();
    		str3+="3";
    		tfCalc.setText(str3);
    	});
    	//4
    	Four.setOnAction(e->{
    		String str4=tfCalc.getText();
    		str4+="4";
    		tfCalc.setText(str4);
    	});
    	//5
    	Five.setOnAction(e->{
    		String str5=tfCalc.getText();
    		str5+="5";
    		tfCalc.setText(str5);
    	});
    	//6
    	Six.setOnAction(e->{
    		String str6=tfCalc.getText();
    		str6+="6";
    		tfCalc.setText(str6);
    	});
    	//7
    	Seven.setOnAction(e->{
    		String str7=tfCalc.getText();
    		str7+="7";
    		tfCalc.setText(str7);
    	});
    	//8
    	Eight.setOnAction(e->{
    		String str8=tfCalc.getText();
    		str8+="8";
    		tfCalc.setText(str8);
    	});
    	//9
    	Nine.setOnAction(e->{
    		String str9=tfCalc.getText();
    		str9+="9";
    		tfCalc.setText(str9);
    	});
    	//+
    	Addition.setOnAction(e->{
    		String strA=tfCalc.getText();
    		strA+="+";
    		tfCalc.setText(strA);
    	});
    	//-
    	Subtraction.setOnAction(e->{
    		String strS=tfCalc.getText();
    		strS+="-";
    		tfCalc.setText(strS);
    	});
    	//×
    	Multiplication.setOnAction(e->{
    		String strM=tfCalc.getText();
    		strM+="×";
    		tfCalc.setText(strM);
    	});
    	//÷
    	Division.setOnAction(e->{
    		String strD=tfCalc.getText();
    		strD+="÷";
    		tfCalc.setText(strD);
    	});
    	//.
    	Point.setOnAction(e->{
    		String strP=tfCalc.getText();
    		strP+=".";
    		tfCalc.setText(strP);
    	});

    	//(
    	RBrackets.setOnAction(e->{
    		String strRB=tfCalc.getText();
    		strRB+="(";
    		tfCalc.setText(strRB);
    	});
    	//)
    	LBrackets.setOnAction(e->{
    		String strLB=tfCalc.getText();
    		strLB+=")";
    		tfCalc.setText(strLB);
    	});
    	//C
    	Clear.setOnAction(e->{
    		tfCalc.setText("");
    	});
    	//=
    	//計算結果を表示
    	Answer.setOnAction(e-> Calculation());
    }


    //受け取った式を計算するクラス
    
    //ボタンで作った式トークンを、逆ポーランド記法に直しスタックやキューを用いる。
    public void Calculation() {
    	System.out.println("入力："+ tfCalc.getText()) ;
    	StringStack stk=new StringStack(80);	//stack
		StringQueue que=new StringQueue(80);	//queue

		String strcalc=tfCalc.getText();	//式を受け取る。
		strcalc=strcalc.replace("×", "*");	//×を＊に
    	strcalc=strcalc.replace("÷", "/");	//÷を/に

    	String regex="\\d+(?:\\.\\d+)?";	//正規表現
    	Pattern ptn=Pattern.compile(regex);
    	String token;	//token
    	int counter=strcalc.length();	//strcalcの長さ
    	int i=0;	//繰返カウンター
    	int kakcounter=0;	//(,)カウンター
    	int fc=0;

    	
    	//逆ポーランド記法に変換
    	while(i<counter) {
    		token=strcalc.substring(i,i+1);	//取り出す。
    		//トークンが(,)ならkakcounter++
    		if(token.equals("(") || token.equals(")"))
    			kakcounter++;
    		//トークンが+,-,*,/なら,をエンキュ―
    		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
    			que.enque(",");
    			fc++;//,の数
    		}
    		i++;

    		Matcher mt=ptn.matcher(token);
    		boolean result=mt.matches();

    		//トークンが.ならエンキュ―
    		if(token.contentEquals("."))
    			que.enque(token);

    		//数字ならエンキュ―
    		else if(result) {
    			try {
    				que.enque(token);
    			}catch(StringQueue.OverflowStringQueueException e) {	//キューが満杯の時
    				break;
    			}


    		//右括弧なら左括弧までスタックからポップ
    		}else if(token.equals(")")) {
    			String lkak="(";
    			String rkak=")";

    			 // 左括弧までスタックからポップしエンキュ―する。
    			while(!(lkak.equals(rkak))) {
    				rkak=stk.pop();
    				if(rkak.equals("("))
    					break;
    				if(!(rkak=="(" || rkak==")"))
    					que.enque(rkak);
    			}
    			//スタックに追加
    		}else if(token.equals("(")) {
    			stk.push(token);
    		}
    		//空ならスタックに追加
    		else if(stk.isEmpty()) {
    			stk.push(token);
    		}

    		/*
    		 * スタックの最上段の演算子よりトークンの演算子の優先順位が低いまたは同じ
    		 * ならスタックをポップしエンキュ―それをスタックが空まで続ける。
    		 */
    		else {
    			while(true) {
    				if(stk.isEmpty()){
    					stk.push(token);
    					break;
    				}
    				String sample=stk.peek();
    				int srank=0;	//stackランク
    				int trank=0;	//tokenランク

    				//スタックの中の演算子のランク付け
    				if(sample.equals("*") || sample.equals("/"))	//  */。。。rank２
    					srank=2;
    				else if(sample.equals("+") || sample.equals("-"))	//  +-。。。rank１
    					srank=1;

    				//トークンの演算子のランク付け
    				if(token.equals("*") || token.equals("/"))
    					trank=2;
    				else if(token.equals("+") || token.equals("-"))
    					trank=1;

    				//NOの場合

    				if((!(trank==srank) &&!(srank>trank)) || sample.equals("(")) {
    					stk.push(token);
    					break;
    				}else {
    					que.enque(stk.pop());
    				}
    			}
    		}
    	}
    	//スタックから出す
    	for(int j=0;j<stk.remainnum()+1;j++) {
    		//System.out.print(stk.peek());
    		que.enque(stk.pop());
    	}
    	//,をpush
    	que.enque(",");

    	//StringBuilderクラス
    	StringBuilder sb=new StringBuilder();

    	//","を消す
    	String t="";
    	String queire="";
    	String pek="";

    	StringQueue que2=new StringQueue(80);
    	for(int k=0;k<que.remainnum();k++) {
    		t=que.deque();
    		if(t.equals(","))
    			continue;
    		Matcher mt2=ptn.matcher(t);	//マッチャー
            boolean result2=mt2.matches();

            if(result2 || t.equals("."))
            	sb.append(t);
    		try {
				pek=que.peek();
			}catch(StringQueue.EmptyStringQueueException e) {	//キューが空の時
			}

    		if(pek.equals(",") || pek.equals("+") || pek.equals("-") 
    				|| pek.equals("*") 	|| pek.equals("/") || t.equals("+") 
    				|| t.equals("-") || t.equals("*") || t.equals("/") ) {
    			queire=new String(sb);
    			if(result2)
    				que2.enque(queire);
    			else
    				que2.enque(t);
    			sb.setLength(0);
    		}
    	}

    	//逆ポーランドの計算
    	i=0;
    	float right;
    	float left;
    	float sum=0;
    	String token2;
    	StringStack stk2=new StringStack(80);
    	//que2回ループ
    	while(i<que2.remainnum()) {
    		//que2からtokenに格納
    		token2=que2.deque();
    		i++;

    		//ptnはwhile外で定義トークンが数字ならresultがtrue
    		Matcher mt=ptn.matcher(token2);
    		boolean result=mt.matches();

    		//数字ならトークンをプッシュ
    		if(result) {
    			stk2.push(token2);
    			//違うのならスタックから数字を取り出し計算
    			}else {
    				right=Float.parseFloat(stk2.pop());
    				left=Float.parseFloat(stk2.pop());

    				if(token2.equals("+"))
    					sum=left+right;
    				else if(token2.equals("-"))
    					sum=left-right;
    				else if(token2.equals("*"))
    					sum=left*right;
    				else if(token2.equals("/"))
    					sum=left/right;
    				stk2.push(String.valueOf(sum));
    			}
    		}

    	//演算結果をlabelに表示
    	tfCalc.setText(stk2.pop());
    	System.out.println("実行結果："+ tfCalc.getText());
    	}

    @Override
    public void start(Stage primaryStage) {

    	//Create the Keyboard
    	CreateKeyboard();
    	btnClicked();

        // Show the Scene
    	Scene scene=new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.setTitle("");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
        System.out.println("完了--RamenOrderApp");
    }

}
