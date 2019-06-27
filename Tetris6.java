/***************************************************************************************************************************************
 *	                                  自寫俄羅斯方塊遊戲 by Alex Chen  2019/6/17                                                       *
 *	                                                                                                                                   *
 *  做法 :                                                                                                                             *
 *                                                                                                                                     *
 *  主要利用 AWT 畫出各種圖形，並配合按鍵事件驅動營造移動及轉換圖形的效果，當中也有使用多執行緒以多工即時處理各種事件，                *
 *  同時以矩陣 21 X 13 檢查並儲存每個位置的資訊，-1 為可通行，0 ~ 9 為各種圖形固定後的儲存資訊， -2 為佔位固定後連成一列時的暫存數字， *
 *  繪圖最小單位則為寬、高各 25 點的正方形，並以此組成各種圖形。                                                                       *
 *                                                                                                                                     *
 *                                               -1 : 未佔位 , 可通行                                                                  *
 *                                               -2 : 佔位連成一列時暫存數字                                                           *
 *                                                0 : 左 Z 形 佔位                                                                     *
 *	                                              1 : 土形 佔位                                                                        *
 *			                                      2 : 小正方形 佔位                                                                    *
 *			                                      3 : 正方形 佔位                                                                      *
 *                                                4 : 長方形 佔位                                                                      *
 *			                                      5 : 左 Z 形 2 佔位                                                                   *
 *			                                      6 : 土形 2 佔位                                                                      *
 *			                                      7 : 土形 3 佔位                                                                      *
 *			                                      8 : 土形 4 佔位                                                                      *
 *			                                      9 : 長方形 2 佔位                                                                    *
 *                                                                                                                                     *
 ***************************************************************************************************************************************/	  
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.imageio.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.applet.AudioClip;
import java.applet.Applet;
import java.net.URI; 
import java.net.URL;

	  /*********************************************
	  *                                            *
	  *   固定方塊顏色維護及測試是否連成一列類別   *
	  *                                            *
      **********************************************/
class color_Thread extends Tetris6 implements Runnable{       // 維護已固定位置之顏色
    public void run()
   {
      
      paint(canvas.getGraphics());
         
   }
   
   public void paint(Graphics g) {
     
      while(true) {
		for(int i = 0; i < 21; i++) {
			int count = 0; 
			for(int j = 0; j < 13; j++) {
			   if(checked[i][j] != -1)
					count++;                                   //    已佔位計數
			   
			   if(count == 13) {                               //    檢查是否連成一列 (滿 13 位)
					for(int k = 0; k < 13; k++) {
						checked[i][k] = -2;                    //    每位先填 -2 (已佔用連成一列 , 填白色)
					}
					for(int l = i; l > 0; l--) {               //     填到最後一列, 最後一列不填
					    for(int m = 0; m < 13; m++) {
						    checked[l][m] = checked[l-1][m];   //     再填上一列數字  
					    }
					}
					
					score += 500;                              //     每消去一列, 加 500 分
					line += 1;                                 //     消去列數增加 1
					Music3();                                  //     播放消去音樂 !!
                }
            }
        }			
				
		for(int i = 0; i < 21; i++) {
			
			for(int j = 0; j < 13; j++) {
			   		
               if(checked[i][j] == 0) {             // 左 Z 形 藍色
				   
				   fillCellColor(g, i, j, Color.blue); 
				   
		       } else if(checked[i][j] == 1) {      // 土形 黃色
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 2) {      // 小正方形 灰色
			   
				   fillCellColor(g, i, j, Color.gray); 
				   
		       } else if(checked[i][j] == 3) {      // 正方形 紅色
			   
				   fillCellColor(g, i, j, Color.red); 
				   
		       } else if(checked[i][j] == 4) {      // 長方形 橘色
			   
				   fillCellColor(g, i, j, Color.orange); 
				   
		       } else if(checked[i][j] == 5) {      // 左 Z 形 2 藍色
			   
				   fillCellColor(g, i, j, Color.blue); 
				   
		       } else if(checked[i][j] == 6) {      // 土形 2 黃色
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 7) {      // 土形 3 黃色
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 8) {      // 土形 4 黃色
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 9) {      // 長方形 2 橘色
			   
				   fillCellColor(g, i, j, Color.orange); 
				   
		       } else if(checked[i][j] == -2) {      // 消去的方塊設 2 白色
			   
				   fillCellColor(g, i, j, Color.white); 
				   
		       } 
			   
			}
			
		}
		
	  }
	  
		
   }
   void fillCellColor(Graphics g, int i, int j, Color color) {            //            填單位方塊顏色函式
	               int x = 155 + 25 * j;
				   int y = 100 + 25 * i;
				   g.setColor(color);
				   g.fillRect(x,y,25,25);
				   g.setColor(Color.white);
				   g.drawRect(x,y,25,25);
   }
}
	  /*********************************************
	  *                                            *
	  *           外框維護及資訊顯示類別           *
	  *                                            *
      **********************************************/
class frame_Thread extends Tetris6 implements Runnable{           // 維護外框
    public void run()
   {

      paint(canvas.getGraphics());
         
   }
   
   public void paint(Graphics g) {

      while(true) {
        for(int x=130 ; x<=130; x+=25)                     // 左邊框
            for(int y=100 ; y<=625; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		for(int x=130 ; x<=480; x+=25)                     // 下邊框
            for(int y=625 ; y<=625; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		for(int x=480 ; x<=480; x+=25)                     // 右邊框
            for(int y=100 ; y<=625; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		for(int x=130 ; x<=480; x+=25)                     // 上邊框
            for(int y=75 ; y<=75; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		str2 = String.valueOf(score);
		str3 = String.valueOf(line);
		String str4 = " LINE COUNT : ";
		g.setFont(new Font("Arial",Font.PLAIN,18));
        g.drawString(str,500,100);                       // 顯示 SCORE
		g.clearRect(560,110,80,25);                      // 清除顯示範圍, 避免?影
		g.drawString(str2,560,135);
		g.drawString(str4,500,170);                      // 顯示 LINE COUNT
		g.clearRect(560,180,80,25);                      // 清除顯示範圍, 避免?影
		g.drawString(str3,560,205);
		
		g.setFont(new Font("Arial",Font.BOLD,15));
		g.drawString(" \u2191    UP ",25,540);            // 顯示向上箭頭
		g.drawString(" \u2190   LEFT",25,565);            // 顯示向左箭頭
		g.drawString(" \u2192   RIGHT ",25,590);          // 顯示向右箭頭
		g.drawString(" \u2193    DOWN ",25,615);          // 顯示向下箭頭
		g.drawString(" SPACE change ",17,640);            // 用空白鍵變換圖形
		g.drawRect(15, 625, 60, 20);         
	  }
   }
}
	  /*********************************************
	  *                                            *
	  *       持續送出方塊及測試是否成功類別       *
	  *                                            *
      **********************************************/
class loop_Thread extends Tetris6 implements Runnable{           // 持續送出方塊的執行緒 
      
   public void run()
   {
	   
	  Music();                              // 播放背景音樂
      update(canvas.getGraphics());
       
   }

   public void paint(Graphics g) {
        		
        for(y = 100; y <= 625; y += 25) {
			
			if(line >= 5) {                                              // 檢查是否已消去 5 條連成一列
				System.out.println(" YOU PASS !!! ");                    // 在控制台顯示成功訊息
		        g.setColor(Color.green);
	            g.setFont(new Font("Arial",Font.PLAIN,18)); 
                g.drawString(" You PASS !!! ",250,75);                   // 在視窗上方顯示成功訊息
		        g.setFont(new Font("Arial",Font.PLAIN,50));
                g.drawString(" You PASS !!! ",180,250);                  // 在視窗中間顯示成功訊息
		        Music_Stop();
		        Music_pass();
				break;                                                   // 成功即跳出迴圈
			}
				
		    if(y == 100) {                                        // 如到迴圈開始處
			   
			   index2 = (int)(Math.random()*10);                  // index2 : 取下一欲顯示圖形
			   index = (int)(queue.poll());                       // index : 從佇列中取出目前欲顯示圖形
			   queue.offer(index2);                               // index2 放入佇列等候顯示 
			   
			}
			
		    int px = (x - 155) / 25;        // 計算行座標
			
		    if(px+1 < 13 && (checked[0][px] != -1 || checked[0][px+1] != -1)) {      // 檢查第一個位置是否已佔用到頂
					   
					   break;               // 佔用即跳出迴圈
			}
			
            try {
               Thread.sleep(300);           // 延遲 0.3 秒   (控制方塊下降速度)
            } catch(InterruptedException e) {
               System.out.println(" do something ... ");
            }
            
      
                    if(index == 0) {                              // 清除之前圖形
                         shape00_fix(g);      // 修補左 Z 形
                    }
                    else if(index == 1) {
                         shape10_fix(g);      // 修補土形
                    }
                    else if(index == 2) {
                         shape20_fix(g);      // 修補小正方形
                    }
                    else if(index == 3) {
                         shape30_fix(g);      // 修補正方形
                    }
                    else if(index == 4) {
                         shape40_fix(g);      // 修補長方形
                    }
					else if(index == 5) {
                         shape01_fix(g);      // 修補左 Z 形 2
                    }
					else if(index == 6) {
                         shape11_fix(g);      // 修補土形 2
                    }
					else if(index == 7) {
                         shape12_fix(g);      // 修補土形 3
                    }
					else if(index == 8) {
                         shape13_fix(g);      // 修補土形 4
                    }
					else if(index == 9) {
                         shape41_fix(g);      // 修補長方形 2
                    }
               
            if(y == 625) {        // 是否最後一條線 ?

                   y = 100;
                                          
            }
            
               if(index == 0) {                                  // 畫圖形
       
                     shape00(g);      // 左 Z 形
               }
  
               else if(index == 1) {

                     shape10(g);      // 土形

               }

               else if(index == 2) {     

                     shape20(g);      // 小正方形

               }
               else if(index == 3) {

                     shape30(g);      // 正方形

               }
               else if(index == 4) {

                     shape40(g);      // 長方形

               }
               else if(index == 5) {

                     shape01(g);      // 左 Z 形 2

               }
			   else if(index == 6) {

                     shape11(g);      // 土形 2

               }
			   else if(index == 7) {

                     shape12(g);      // 土形 3

               }
			   else if(index == 8) {

                     shape13(g);      // 土形 4

               }
			   else if(index == 9) {

                     shape41(g);      // 長方形 2

               }
			   
            printNext(g);                           // 在左邊列印下次欲顯示圖形
        }
		
		if(line < 5) {                              // 如果填滿最後一格跳出後, 仍不足 5 條連成一列, 顯示失敗訊息
			
		System.out.println(" YOU LOSE !!! ");       // 在控制台顯示失敗訊息
		g.setColor(Color.red);
	    g.setFont(new Font("Arial",Font.PLAIN,18));
        g.drawString(" You Lose !!! ",250,75);      // 在視窗上方顯示失敗訊息
		g.setFont(new Font("Arial",Font.PLAIN,50));
        g.drawString(" You Lose !!! ",180,250);     // 在視窗中間顯示失敗訊息
		
		Music_Stop();                               // 停止播放背景音樂
		Music_failed();                             // 播放失敗音樂
		
		for(int y=100 ; y<=600; y+=25)              // 失敗後,全部轉回灰色的方塊
		    for(int x=155 ; x<=455; x+=25)
            {
                try {
                     Thread.sleep(100);
                } catch(InterruptedException e) {
                     System.out.println(" do something ... ");
                }
                g.setColor(Color.gray);
                g.fillRect(x,y,25,25);
                g.setColor(Color.white);
                g.drawRect(x,y,25,25);

            }
			
		}
		
   }
      public void printNext(Graphics g) {                           // 左邊列印下次欲顯示圖形函式    
		int x = 12;
		int y = 180;
		
        g.setColor(Color.red);
		g.setFont(new Font("Arial",Font.ITALIC,22));
        g.drawString(" NEXT ... ",0,150);
		
	    g.setColor(Color.white);
	    g.fillRect(12,180,100,100);

	        if(index2 == 0) {
     	   
               shape00_1(g,x,y);      // 左 Z 形
			   
		    }
		    if(index2 == 1) {
				
     	       shape10_1(g,x,y);      // 土形
			   
		    }
		    if(index2 == 2) {
				
     	       shape20_1(g,x,y);      // 小正方形
			   
		    }
		    if(index2 == 3) {
				
               shape30_1(g,x,y);      // 正方形
			   
		    }
		    if(index2 == 4) {
				
     	       shape40_1(g,x,y);      // 長方形
			   
		    }
		    if(index2 == 5) {
				
     	    shape01_1(g,x,y);         // 左 Z 形 2
			
		    }
		    if(index2 == 6) {
				
     	       shape11_1(g,x,y);      // 土形 2
			   
		    }
		    if(index2 == 7) {
				
     	       shape12_1(g,x,y);      // 土形 3
			   
		    }
		    if(index2 == 8) {
				
     	       shape13_1(g,x,y);      // 土形 4
			   
		    }
		    if(index2 == 9) {
				
     	       shape41_1(g,x,y);      // 長方形 2
			   
		    }
   } 
}  

	  /*********************************************
	  *                                            *
	  *               Tetris6 主類別               *
	  *                                            *
      **********************************************/
public class Tetris6 extends Canvas implements WindowListener,KeyListener
{
   static Frame frm = new Frame("Tetris6");
   static Tetris6 canvas = new Tetris6();
   static int x = 255; 
   static int y = 75;
   static int score = 0;
   static int line = 0;
   String str = " YOUR SCORE : ";
   String str2 = "";
   String str3 = "";
   
   static int index = (int)(Math.random() * 10);
   static int index2 = 0;
   public static ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<Object>();
   

   static int[][] checked = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       // 用來判斷是否可通行, 並儲存該位置為何種圖形顏色
                              {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       // -1 : 未佔位 , 可通行
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       // -2 : 佔位連成一列時暫存數字
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  0 : 左 Z 形 佔位 
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  1 : 土形 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  2 : 小正方形 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  3 : 正方形 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  4 : 長方形 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  5 : 左 Z 形 2 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  6 : 土形 2 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  7 : 土形 3 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  8 : 土形 4 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  9 : 長方形 2 佔位
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}  };


   public static void main(String args[])
   {
      frm.setSize(645,700);
	  frm.add(canvas,BorderLayout.CENTER);
	  frm.addWindowListener(canvas);             // 設定 canvas 為 Windows Event 事件監聽者
      frm.addKeyListener(canvas);                // 設定 canvas 為 Key Event 事件監聽者
	  
	  queue.offer(index);                        // 佇列填入初值(第一個顯示圖形)

      frm.setVisible(true);

      loop_Thread loop_thread = new loop_Thread();
      frame_Thread frame_thread = new frame_Thread();	
      color_Thread color_thread = new color_Thread();
	  
      Thread t1 = new Thread(loop_thread);
	  Thread t2 = new Thread(frame_thread);
	  Thread t3 = new Thread(color_thread);
	  
	  t1.start();           // 持續送出方塊的執行緒 
	  t2.start();           // 維護外框執行緒
	  t3.start();           // 維護已固定位置之顏色執行緒
	  
   }

   public void paint(Graphics g)
   {
        for(int x=155 ; x<=455; x+=25)              // 不斷更新背景顏色 , 可避免殘影發生
            for(int y=100 ; y<=600; y+=25) 
            {

                g.setColor(Color.white);
                g.fillRect(x,y,25,25);
                
            }
   }
	  /***************************************
	  *                                      *
	  *              音樂程式區              *
	  *                                      *
      ****************************************/   
 void Music(){ //注意，java只能播放無損音質，如.wav這種格式                                    // 背景音樂
      try { 
	       File f = new File(".\\background.wav"); //絕對路徑 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //解析路徑 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //單曲循環 
		   
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}
 void Music_Stop(){ //注意，java只能播放無損音質，如.wav這種格式                               // 停止播放背景音樂背景音樂 
      try { 
	       File f = new File(".\\background.wav"); //絕對路徑 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //解析路徑 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //單曲循環 
		   aau.stop(); 
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}
void Music2(){ //注意，java只能播放無損音質，如.wav這種格式                                   // 磚塊推疊音樂  
      try { 
	       File f = new File(".\\stack.wav"); //絕對路徑 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //解析路徑 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //單曲循環 
		   try {
               Thread.sleep(300);
            } catch(InterruptedException e) {
               System.out.println(" do something ... ");
            }
		   aau.stop(); 
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}
void Music3(){ //注意，java只能播放無損音質，如.wav這種格式                                     // 磚塊連成一列消去音樂   
      try { 
	       File f = new File(".\\delete.wav"); //絕對路徑 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //解析路徑 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //單曲循環 
		   try {
               Thread.sleep(300);
            } catch(InterruptedException e) {
               System.out.println(" do something ... ");
            }
		   aau.stop(); 
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}
void Music_failed(){ //注意，java只能播放無損音質，如.wav這種格式                                // 失敗音樂 
      try { 
	       File f = new File(".\\fail.wav"); //絕對路徑 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //解析路徑 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //單曲循環 
		   try {
               Thread.sleep(3000);
            } catch(InterruptedException e) {
               System.out.println(" do something ... ");
            }
		   aau.stop(); 
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}
void Music_pass(){ //注意，java只能播放無損音質，如.wav這種格式                                  // 過關音樂  
      try { 
	       File f = new File(".\\pass.wav"); //絕對路徑 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //解析路徑 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //單曲循環 
		   try {
               Thread.sleep(3000);
            } catch(InterruptedException e) {
               System.out.println(" do something ... ");
            }
		   aau.stop(); 
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}

	  /***************************************
	  *                                      *
	  *         左 Z 形 繪圖規則程式         *
	  *                                      *
      ****************************************/
   public void shape00(Graphics g) {
   
					  int py = (y-100)/25;                        // 計算列座標
					  int px = (x-155)/25;                        // 計算行座標

                      if(x >= 380) {                              // 左 Z 形 右移邊界限制
		                  x = 380;
		              } 
                      if(y >= 600) {                              // 左 Z 形 下移邊界限制
		                  y = 575;
		              }                      
					  if(py > -1 && px > -1 && py+2 < 21 && px+3 < 13 && (checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1       // 測試目前預填位置下方
					                                                       || checked[py+2][px+3] != -1 || checked[py+1][px] != -1)) { // 有無其他圖形
					 
                         shape00_1(g,x,y);                        // 呼叫畫左 Z 形核心函式	
						  
						 checked[py][px] = 0;                     // 下方有其他圖形則填 左 Z 形 代表號碼 "0"
					     checked[py][px+1] = 0;
						 checked[py][px+2] = 0;
						 checked[py+1][px+1] = 0;
						 checked[py+1][px+2] = 0;
						 checked[py+1][px+3] = 0;
							 
						 y = 75;                                  // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
						
						 Music2();                                // 播放磚塊推疊音樂
						 return;
					  }
  
                      shape00_1(g,x,y);                           // 呼叫畫左 Z 形核心函式

   }
	  /***************************************
	  *                                      *
	  *      左 Z 形 移動時清除修補程式      *
	  *                                      *
      ****************************************/
   public void shape00_fix(Graphics g) {
						 int py = (y-100)/25;                     // 計算列座標
					     int px = (x-155)/25;                     // 計算行座標	
					  if(y == 600 && py-1 > -1 && px+3 < 13) {                           // 是否到達下界限 ?
						     							 
							 //shape00_1(g,x,y);                        // 呼叫畫左 Z 形核心函式
							 
							 checked[py-1][px] = 0;                 // 是的話 , 在 左 Z 形 座標位置填代表號碼 "0"
							 checked[py-1][px+1] = 0;
							 checked[py-1][px+2] = 0;
						     checked[py][px+1] = 0;
							 checked[py][px+2] = 0;
							 checked[py][px+3] = 0;
							 y = 100;                             // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                             index2 = (int)(Math.random() * 10);  // 隨機選取圖形
						     index = (int)queue.poll();
						     queue.offer(index2);
							 
                             Music2();                            // 播放磚塊推疊音樂
							 return;
                      }    
                         
					 
						 if(py > -1 && px > -1 && py+2 < 21 && px+3 < 13 && (checked[py+2][px+1] != -1 // 檢查預放位置下面有無佔位圖形
						                 || checked[py+2][px+2] != -1 || checked[py+2][px+3] != -1)) {
						 
						    shape00_fix_1(g,x,y);                 // 呼叫 左 Z 形 清除修補核心程式
												 
						    return;
						 }
						 
                            shape00_fix_1(g,x,y);                 // 呼叫 左 Z 形 清除修補核心程式
						 
   }
	  /***************************************
	  *                                      *
	  *         左 Z 形 2 繪圖規則程式       *
	  *                                      *
      ****************************************/
   public void shape01(Graphics g) {
   
   					  int py = (y-100)/25;                        // 計算列座標
					  int px = (x-155)/25;                        // 計算行座標	
                      
                      if(x >= 430) {                              // 左 Z 形 2 右移邊界限制
		                  x = 430;
		              }
                      if(y >= 550) {                              // 左 Z 形 2 下移邊界限制
		                  y = 525;
		              }
					  
					  if(py > -1 && px > -1 && py+4 < 21 &&  px+1 < 13 && (checked[py+4][px] != -1 || checked[py+3][px+1] != -1)) { // 測試目前預填位置下方
						                                                                                                            // 有無其他圖形
                         shape01_1(g,x,y);                        // 呼叫畫左 Z 形 2 核心函式
							  
						 checked[py][px+1] = 5;                   // 下方有其他圖形則填 左 Z 形 2 代表號碼 "5"
						 checked[py+1][px] = 5;
						 checked[py+1][px+1] = 5;
						 checked[py+2][px] = 5;
						 checked[py+2][px+1] = 5;
						 checked[py+3][px] = 5; 
							 
						 y = 75;                                  // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
                         
                         Music2();                                // 播放磚塊推疊音樂						 
						 return;
						 
					  }

                         shape01_1(g,x,y);                        // 呼叫畫左 Z 形 2 核心函式
                      
   }
	  /***************************************
	  *                                      *
	  *     左 Z 形 2 移動時清除修補程式     *
	  *                                      *
      ****************************************/
   public void shape01_fix(Graphics g) {
						 int py = (y-100)/25;                 // 計算列座標
					     int px = (x-155)/25;                 // 計算行座標	
					  if(y == 550 && py+2 < 21 && px+1 < 13) {                       // 是否到達下界限 ?
						     							 
							 //shape01_1(g,x,y);                        // 呼叫畫左 Z 形 2 核心函式
							 
							 checked[py-1][px+1] = 5;           // 是的話 , 在 左 Z 形 2 座標位置填代表號碼 "5"
							 checked[py][px] = 5;
							 checked[py][px+1] = 5;
							 checked[py+1][px] = 5;
							 checked[py+1][px+1] = 5;
						     checked[py+2][px] = 5; 
                             y = 100;                         // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                            index2 = (int)(Math.random() * 10);// 隨機選取圖形
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                         // 播放磚塊推疊音樂
							return;
                      }   
                         
						 
					 
						 if(py > -1 && px > -1 && py+4 < 21 && px+1 < 13 && (checked[py+4][px] != -1  // 檢查預放位置下面有無佔位圖形
						                                            || checked[py+3][px+1] != -1)) {
						 
						    shape01_fix_1(g,x,y);             // 呼叫 左 Z 形 2 清除修補核心程式
						 
						    return;
						 }	
						 shape01_fix_1(g,x,y);                // 呼叫 左 Z 形 2 清除修補核心程式
   }
	  /***************************************
	  *                                      *
	  *          土形 繪圖規則程式           *
	  *                                      *
      ****************************************/
   public void shape10(Graphics g) {
   
					  int py = (y-100)/25;                       // 計算列座標
					  int px = (x-155)/25;                       // 計算行座標	
					  
                      if(x >= 405) {                             // 土形 右移邊界限制
		                  x = 405;
		              }
                      if(y >= 600) {                             // 土形 下移邊界限制
		                  y = 575;
		              }
					  
					  if(py > -1 &&  px > -1 && py+2 < 21 && px+2 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1  // 測試目前預填位置下方
					                                                                           || checked[py+2][px+2] != -1)) {  // 有無其他圖形
						 
                         shape10_1(g,x,y);                       // 呼叫畫土形核心函式
						 
						 checked[py][px+1] = 1;                  // 下方有其他圖形則填 土形 代表號碼 "1"
					     checked[py+1][px] = 1;
						 checked[py+1][px+1] = 1;
						 checked[py+1][px+2] = 1;
							 
						 y = 75;                                 // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
                        
                         Music2();                               // 播放磚塊推疊音樂						 
						 return;
					  }
	  
					  shape10_1(g,x,y);                          // 呼叫畫土形核心函式
                      
   }
	  /***************************************
	  *                                      *
	  *       土形 移動時清除修補程式        *
	  *                                      *
      ****************************************/
   public void shape10_fix(Graphics g) {
						 int py = (y-100)/25;                    // 計算列座標
					     int px = (x-155)/25;                    // 計算行座標
					  if(y >= 600 && py-1 > -1 && px+2 < 13) {                          // 是否到達下界限 ?
						    							 
							 //shape10_1(g,x,y);                          // 呼叫畫土形核心函式
							 
						     checked[py-1][px+1] = 1;              // 是的話 , 在 土形 座標位置填代表號碼 "1"
							 checked[py][px] = 1;
							 checked[py][px+1] = 1;
							 checked[py][px+2] = 1;
							 
                             y = 100;                            // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                             index2 = (int)(Math.random() * 10); // 隨機選取圖形
						     index = (int)queue.poll();
						     queue.offer(index2);
							
							Music2();                            // 播放磚塊推疊音樂
							return;
                      }   
                         
						 
						 if(py > -1 &&  px > -1 && px+2 < 13 && py+2 < 21 && (checked[py+2][px] != -1  // 檢查預放位置下面有無佔位圖形
						                || checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1)) {
						 
                            shape10_fix_1(g,x,y);                // 呼叫 土形 清除修補核心程式
					 
						    return;
						 }	
                         
                            shape10_fix_1(g,x,y);                // 呼叫 土形 清除修補核心程式
                         
   } 
	  /***************************************
	  *                                      *
	  *        土形 2 繪圖規則程式           *
	  *                                      *
      ****************************************/
   public void shape11(Graphics g) {
   
					  int py = (y-100)/25;                      // 計算列座標
					  int px = (x-155)/25;                      // 計算行座標	
  					  
                      if(x >= 430) {                            // 土形 2 右移邊界限制
		                  x = 430;
		              }
                      if(y >= 575) {                            // 土形 2 下移邊界限制
		                  y = 550;
		              }	
                       
					  
					  if(py > -1 &&  px > -1 && px+1 < 13 && py+3 < 21 && (checked[py+3][px] != -1 || checked[py+2][px+1] != -1)) {  // 測試目前預填位置下方
						                                                                                                             // 有無其他圖形
                         shape11_1(g,x,y);                     // 呼叫畫土形 2 核心函式					  
	 
    					 checked[py][px] = 6;                  // 下方有其他圖形則填 土形 2 代表號碼 "6"
                         checked[py+1][px] = 6;
                         checked[py+2][px] = 6;
                         checked[py+1][px+1] = 6;	
							 
					     y = 75;                               // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
                         	
                         Music2();                             // 播放磚塊推疊音樂						 
						 return;
					  }							
	  
                      shape11_1(g,x,y);                        // 呼叫畫土形 2 核心函式		

   }
	  /***************************************
	  *                                      *
	  *     土形 2 移動時清除修補程式        *
	  *                                      *
      ****************************************/
   public void shape11_fix(Graphics g) {
						 int py = (y-100)/25;                  // 計算列座標
					     int px = (x-155)/25;                  // 計算行座標
                      if(y == 575 && py+1 < 21 && px+1 < 13) {                        // 是否到達下界限 ?
						     							 
							 //shape11_1(g,x,y);                        // 呼叫畫土形 2 核心函式	
							 
						     checked[py-1][px] = 6;              // 是的話 , 在 土形 2 座標位置填代表號碼 "6"
                             checked[py][px] = 6;
                             checked[py+1][px] = 6;
                             checked[py][px+1] = 6;							 
                             y = 100;                          // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                            index2 = (int)(Math.random() * 10);// 隨機選取圖形
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // 播放磚塊推疊音樂
							return;
                      }				   
                          
						 

						 
						 if(py > -1 && px > -1 && py+3 < 21 && px+1 < 13 && (checked[py+3][px] != -1  // 檢查預放位置下面有無佔位圖形
						                                            || checked[py+2][px+1] != -1)) {
	                     
						    shape11_fix_1(g,x,y);              // 呼叫 土形 2 清除修補核心程式
						 
						    return;
						 }
						 
						 shape11_fix_1(g,x,y);                 // 呼叫 土形 2 清除修補核心程式
						 
   }
	  /***************************************
	  *                                      *
	  *        土形 3 繪圖規則程式           *
	  *                                      *
      ****************************************/
   public void shape12(Graphics g) {
   
				      int py = (y-100)/25;                     // 計算列座標
				      int px = (x-155)/25;                     // 計算行座標	

                      if(x >= 405) {                           // 土形 3 右移邊界限制
		                  x = 405;
		              }
                      if(y >= 600) {                           // 土形 3 下移邊界限制
		                  y = 575;
		              }
                      
				      if(py > -1 &&  px > -1 &&  py+2 < 21 && px+2 < 13 && (checked[py+1][px] != -1 || checked[py+2][px+1] != -1   // 測試目前預填位置下方
					                                                                            || checked[py+1][px+2] != -1)) {   // 有無其他圖形
						 
                          shape12_1(g,x,y);                    // 呼叫畫土形 3 核心函式
							 
					      checked[py][px] = 7;                 // 下方有其他圖形則填 土形 3 代表號碼 "7"
                          checked[py][px+1] = 7;
                          checked[py][px+2] = 7;
                          checked[py+1][px+1] = 7;
                         	
                          Music2();                            // 播放磚塊推疊音樂						 
				          y = 75;                              // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
							 
					      return;
				      }
                     
                      shape12_1(g,x,y);                        // 呼叫畫土形 3 核心函式
					  
   }
	  /***************************************
	  *                                      *
	  *     土形 3 移動時清除修補程式        *
	  *                                      *
      ****************************************/
   public void shape12_fix(Graphics g) {
						 int py = (y-100)/25;                  // 計算列座標
					     int px = (x-155)/25;                  // 計算行座標
                      if(y == 600 && py-1 > -1 && px+2 < 13) {                        // 是否到達下界限 ?
						     							 
							 //shape12_1(g,x,y);                    // 呼叫畫土形 3 核心函式
							 
						     checked[py-1][px] = 7;              // 是的話 , 在 土形 3 座標位置填代表號碼 "7"
                             checked[py-1][px+1] = 7;
                             checked[py-1][px+2] = 7;
                             checked[py][px+1] = 7;							 
                             y = 100;                          // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                            index2 = (int)(Math.random() * 10);// 隨機選取圖形
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // 播放磚塊推疊音樂
							return;
                      }  
					 
						 
						 if(py > -1 &&  px > -1 &&  py+2 < 21 && px+2 < 13 && (checked[py+1][px] != -1  // 檢查預放位置下面有無佔位圖形
						                 || checked[py+2][px+1] != -1 || checked[py+1][px+2] != -1)) {
						 
						    shape12_fix_1(g,x,y);              // 呼叫 土形 3 清除修補核心程式

						    return;
						 }
						 
						 shape12_fix_1(g,x,y);                 // 呼叫 土形 3 清除修補核心程式
							 
   }
	  /***************************************
	  *                                      *
	  *        土形 4 繪圖規則程式           *
	  *                                      *
      ****************************************/
   public void shape13(Graphics g) {
   
					  int py = (y-100)/25;                    // 計算列座標
					  int px = (x-155)/25;                    // 計算行座標
					 
                      if(x >= 430) {                          // 土形 4 右移邊界限制
		                  x = 430;
		              }	
                      if(y >= 575) {                          // 土形 4 下移邊界限制
		                  y = 550;
		              }					  
                      
					  if(py > -1 &&  px > -1 &&  px+1 < 13 &&  py+3 < 21 && (checked[py+2][px] != -1 || checked[py+3][px+1] != -1)) {   // 測試目前預填位置下方
				                                                                                                                        // 有無其他圖形
                         shape13_1(g,x,y);                    // 呼叫畫土形 4 核心函式		
				 
						 checked[py+1][px] = 8;               // 下方有其他圖形則填 土形 4 代表號碼 "8"
                         checked[py][px+1] = 8;
                         checked[py+1][px+1] = 8;
                         checked[py+2][px+1] = 8;
						 
						 y = 75;                              // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
                         
                         Music2();                            // 播放磚塊推疊音樂						 
						 return;
						 
					  }
			  
                      shape13_1(g,x,y);                       // 呼叫畫土形 4 核心函式
							  
   }

	  /***************************************
	  *                                      *
	  *     土形 4 移動時清除修補程式        *
	  *                                      *
      ****************************************/
   public void shape13_fix(Graphics g) {
						 int py = (y-100)/25;                  // 計算列座標
					     int px = (x-155)/25;                  // 計算行座標
                      if(y == 575) {                        // 是否到達下界限 ?
						     							 
							 //shape13_1(g,x,y);                    // 呼叫畫土形 4 核心函式
							 
						     checked[py][px] = 8;            // 是的話 , 在 土形 4 座標位置填代表號碼 "8"
                             checked[py-1][px+1] = 8;
                             checked[py][px+1] = 8;
                             checked[py+1][px+1] = 8;							 
                             y = 100;                          // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                            index2 = (int)(Math.random() * 10);// 隨機選取圖形
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // 播放磚塊推疊音樂
							return;
                      }   
                         
						 

					  
						 if(py > -1 &&  px > -1 && px+1 < 13 && py+3 < 21 && (checked[py+2][px] != -1  // 檢查預放位置下面有無佔位圖形
						                                             || checked[py+3][px+1] != -1)) {
						 
						    shape13_fix_1(g,x,y);              // 呼叫 土形 4 清除修補核心程式

						    return;
						 }
						 
						 shape13_fix_1(g,x,y);                 // 呼叫 土形 4 清除修補核心程式
   }
	  /***************************************
	  *                                      *
	  *        小正方形 繪圖規則程式         *
	  *                                      *
      ****************************************/
   public void shape20(Graphics g) {

						 int py = (y-100)/25;                 // 計算列座標
					     int px = (x-155)/25;                 // 計算行座標
						 
						 if(y == 600) {
							 return;
						 }
						 if(py > -1 &&  px > -1 &&  py+1 < 21 && checked[py+1][px] != -1) {     // 如果下一行為 1 (已佔用) , 畫出圖形後, 跳出
						 	 											 
							shape20_1(g,x,y);                 // 呼叫畫小正方形核心函式 
							
							checked[py][px] = 2;              // 下方有其他圖形則填 小正方形 代表號碼 "2"
							
							y = 75;                           // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
                            
                            Music2();                         // 播放磚塊推疊音樂							
						    return;
						 }
						 
                    
                         shape20_1(g,x,y);                    // 呼叫畫小正方形核心函式
					 
   }
	  /***************************************
	  *                                      *
	  *      小正方形 移動時清除修補程式     *
	  *                                      *
      ****************************************/
   public void shape20_fix(Graphics g) {
						 int py = (y-100)/25;                   // 計算列座標
					     int px = (x-155)/25;                   // 計算行座標
						 if(y == 625 && py-1 > -1) {                         // 是否到達下界限 ?
						     							 
							 //shape20_1(g,x,y);                 // 呼叫畫小正方形核心函式
							 
						     checked[py-1][px] = 2;               // 是的話 , 在 小正方形 座標位置填代表號碼 "2"
							 
							 shape20_1(g,x,y-25);

                             y = 100;                           // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                            index2 = (int)(Math.random() * 10); // 隨機選取圖形
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                           // 播放磚塊推疊音樂
							return;
                         }	                     
						                    
                       
						 if(py > -1 &&  px > -1 &&  py+1 < 21 && checked[py+1][px] != -1) { // 檢查預放位置下面有無佔位圖形
						    							
							shape20_fix_1(g,x,y);               // 呼叫 小正方形 清除修補核心程式
						 
							return;
						 }
						 
						 shape20_fix_1(g,x,y);                  // 呼叫 小正方形 清除修補核心程式

   }
	  /***************************************
	  *                                      *
	  *          正方形 繪圖規則程式         *
	  *                                      *
      ****************************************/
   public void shape30(Graphics g) {
   
					  int py = (y-100)/25;                   // 計算列座標
					  int px = (x-155)/25;                   // 計算行座標

                      if(x >= 430) {                         // 正方形 右移邊界限制
		                  x = 430;
		              }
                      if(y >= 600) {                         // 正方形 下移邊界限制
		                  y = 575;
		              }
                      
					  if(py > -1 &&  px > -1 &&  py+2 < 21 && px+1 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1  )) {   // 測試目前預填位置下方
						                                                                                                                 // 有無其他圖形
                         shape30_1(g,x,y);                   // 呼叫畫正方形核心函式
                         								 
						 checked[py][px] = 3;                // 下方有其他圖形則填 正方形 代表號碼 "3"
                         checked[py][px+1] = 3;	
					     checked[py+1][px] = 3;
						 checked[py+1][px+1] = 3;
							
						 y = 75;                             // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
                         
                         Music2();                           // 播放磚塊推疊音樂						 
						 return;
					  }
				  
                      shape30_1(g,x,y);                      // 呼叫畫正方形核心函式
							  
   }
	  /***************************************
	  *                                      *
	  *      正方形 移動時清除修補程式       *
	  *                                      *
      ****************************************/ 
   public void shape30_fix(Graphics g) {
						 int py = (y-100)/25;                  // 計算列座標
					     int px = (x-155)/25;                  // 計算行座標	   
	   
                      if(y == 600) {                        // 是否到達下界限 ?
						     							 
							 //shape30_1(g,x,y);                   // 呼叫畫正方形核心函式
							 
						     checked[py-1][px] = 3;              // 是的話 , 在 正方形 座標位置填代表號碼 "3"
                             checked[py-1][px+1] = 3;	
							 checked[py][px] = 3;
							 checked[py][px+1] = 3;
							 
                             y = 100;                          // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                            index2 = (int)(Math.random() * 10);// 隨機選取圖形
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // 播放磚塊推疊音樂
							return;
                      }   
                         
						 
						 
						 if(py > -1 &&  px > -1 &&  py+2 < 21 && px+1 < 13 && (checked[py+2][px] != -1  // 檢查預放位置下面有無佔位圖形
						                                            || checked[py+2][px+1] != -1  )) {
						 
                            shape30_fix_1(g,x,y);              // 呼叫 正方形 清除修補核心程式
								
						    return;
							
						 }
						 
						 shape30_fix_1(g,x,y);                 // 呼叫 正方形 清除修補核心程式

   }
	  /***************************************
	  *                                      *
	  *          長方形 繪圖規則程式         *
	  *                                      *
      ****************************************/
   public void shape40(Graphics g) {
   
					   int py = (y-100)/25;                 // 計算列座標
					   int px = (x-155)/25;                 // 計算行座標	

                       if(x >= 380) {                        // 長方形 右移邊界限制
		                   x = 380;
		               }
                       						 					   
					   if(py > -1 &&  px > -1 &&  py+1 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+1] != -1   // 測試目前預填位置下方 
					                                               || checked[py+1][px+2] != -1 || checked[py+1][px+3] != -1 )) {   // 有無其他圖形

                         shape40_1(g,x,y);                  // 呼叫畫長方形核心函式
                         
						 checked[py][px] = 4;               // 下方有其他圖形則填 長方形 代表號碼 "4"
					     checked[py][px+1] = 4;
						 checked[py][px+2] = 4;
						 checked[py][px+3] = 4;
							
						 y = 75;                            // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
                         
                         Music2();                          // 播放磚塊推疊音樂						 
						 return;
					  }
			  
                      shape40_1(g,x,y);                     // 呼叫畫長方形核心函式
						  
   }
	  /***************************************
	  *                                      *
	  *      長方形 移動時清除修補程式       *
	  *                                      *
      ****************************************/ 
   public void shape40_fix(Graphics g) {
						 int py = (y-100)/25;                 // 計算列座標
					     int px = (x-155)/25;                 // 計算行座標	
                       if(y == 625 && px+3 < 13) {                       // 是否到達下界限 ?
						     							 
							 //shape40_1(g,x,y);                  // 呼叫畫長方形核心函式
							 
						     checked[py-1][px] = 4;             // 是的話 , 在 長方形 座標位置填代表號碼 "4"
							 checked[py-1][px+1] = 4;
							 checked[py-1][px+2] = 4;
							 checked[py-1][px+3] = 4;
                             y = 100;                         // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							 
                           index2 = (int)(Math.random() * 10);// 隨機選取圖形
						   index = (int)queue.poll();
						   queue.offer(index2);
							
							Music2();                         // 播放磚塊推疊音樂
							return;
                       }   
                         
					 
						 if(py > -1 && px > -1 && py+1 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+1] != -1  // 檢查預放位置下面有無佔位圖形
						                                           || checked[py+1][px+2] != -1 || checked[py+1][px+3] != -1 )) {
						    
							shape40_fix_1(g,x,y);             // 呼叫 長方形 清除修補核心程式
							
						    return;
						 }
						 
						 shape40_fix_1(g,x,y);                // 呼叫 長方形 清除修補核心程式
						 
   }
	  /***************************************
	  *                                      *
	  *        長方形 2 繪圖規則程式         *
	  *                                      *
      ****************************************/
   public void shape41(Graphics g) {
   
					  int py = (y-100)/25;                  // 計算列座標
					  int px = (x-155)/25;                  // 計算行座標
					  
                      if(y >= 550) {                              // 長方形 2 下移邊界限制
		                  y = 525;
		              }					  
                      
					  if(py > -1 &&  px > -1 &&  py+4 < 21 && (checked[py+4][px] != -1)) {   // 測試目前預填位置下方
   			                                                                                 // 有無其他圖形		 
						   
                         shape41_1(g,x,y);                  // 呼叫畫長方形 2 核心函式
                         	
						 checked[py][px] = 9;               // 下方有其他圖形則填 長方形 2 代表號碼 "9"
                         checked[py+1][px] = 9;
                         checked[py+2][px] = 9;
                         checked[py+3][px] = 9;
                         
                         y = 75;                            // y 設為 75 , 回到迴圈開頭再加 25 則為 100 , 再繼續繪圖
						 Music2();                          // 播放磚塊推疊音樂	
						 return;
					  }
						 
                      shape41_1(g,x,y);                     // 呼叫畫長方形 2 核心函式
						  
   }
	  /***************************************
	  *                                      *
	  *     長方形 2 移動時清除修補程式      *
	  *                                      *
      ****************************************/ 
   public void shape41_fix(Graphics g) {
	   
						 int py = (y-100)/25;                  // 計算列座標
					     int px = (x-155)/25;                  // 計算行座標	   

                      if(y >= 550 && py+2 < 21) {                        // 是否到達下界限 ?

							//shape41_1(g,x,y);                  // 呼叫畫長方形 2 核心函式
							
						    checked[py-1][px] = 9;               // 是的話 , 在 長方形 2 座標位置填代表號碼 "9"
                            checked[py][px] = 9;
                            checked[py+1][px] = 9;
                            checked[py+2][px] = 9;							 
                            y = 100;                           // y 設為 100 , 回到迴圈開頭再加 25 則為 125 , 再繼續清除繪圖
							
                            index2 = (int)(Math.random() * 10);// 隨機選取圖形
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // 播放磚塊推疊音樂
							return;
                      }   
					 
						 
						 if(py > -1 &&  px > -1 &&  py+4 < 21 && (checked[py+4][px] != -1)) { // 檢查預放位置下面有無佔位圖形
						  
						    shape41_fix_1(g,x,y);              // 呼叫 長方形 2 清除修補核心程式
						  
						    return;
						 }
						 
						 shape41_fix_1(g,x,y);                 // 呼叫 長方形 2 清除修補核心程式
						 
   }
   
  
	  /***************************************
	  *                                      *
	  *            繪圖核心程式區            *
	  *                                      *
      ****************************************/   
   public void shape00_1(Graphics g, int x, int y) {              // 畫左 Z 形核心函式
	   
	                  g.setColor(Color.blue);                     // 左 Z 形
                      g.fillRect(x,y,75,25);
                      g.setColor(Color.white);
                      for(int i = x; i < x + 75; i += 25)
                          g.drawRect(i,y,25,25);
                      g.setColor(Color.blue);                     // 左 Z 形
                      g.fillRect(x+25,y+25,75,25);
                      g.setColor(Color.white);
                      for(int i = x+25; i < x + 100; i += 25)
                          g.drawRect(i,y+25,25,25);
					  
   }
   public void shape01_1(Graphics g, int x, int y) {              // 畫左 Z 形 2 核心函式
	                     
					  g.setColor(Color.blue);                     // 左 Z 形 2
                         g.fillRect(x,y,50,100);
						 g.setColor(Color.white);
						 g.fillRect(x,y,25,25);
					     g.fillRect(x+25,y+75,25,25);       
						 g.setColor(Color.white);
                         for(int i = x; i < x + 50; i += 25)
                             for(int j = y; j < y + 100; j += 25)
                                 g.drawRect(i,j,25,25);
							 
   }
   public void shape10_1(Graphics g, int x, int y) {             // 畫土形核心函式
	   
	                  g.setColor(Color.yellow);                  // 土形
                      g.fillRect(x+25,y,25,25);
                      for(int i = x; i < x + 75; i += 25)
                          g.fillRect(i,y+25,25,25);
                      g.setColor(Color.white);
                      g.drawRect(x+25,y,25,25);
                      for(int i = x; i < x + 75; i += 25)
                          g.drawRect(i,y+25,25,25);
					  
   }
   public void shape11_1(Graphics g, int x, int y) {           // 畫土形 2 核心函式
	                 
					  g.setColor(Color.yellow);                // 土形 2
                      g.fillRect(x,y,25,25);
					  g.setColor(Color.white);
					  g.drawRect(x,y,25,25);
					  
					  g.setColor(Color.yellow);
					  g.fillRect(x,y+25,50,50);
				      g.setColor(Color.white);
					  for(int i = x; i < x+50; i += 25)
						  g.drawRect(i,y+25,25,25);
						  
					  g.setColor(Color.yellow);
					  g.fillRect(x,y+50,25,25);
					  g.setColor(Color.white);
					  g.drawRect(x,y+50,25,25);	
					  
					  g.setColor(Color.white);
					  g.fillRect(x+25,y+50,25,25);
					  g.setColor(Color.white);
					  g.drawRect(x+25,y+50,25,25);	
					 
   }
   public void shape12_1(Graphics g, int x, int y) {           // 畫土形 3 核心函式
	   
	                  g.setColor(Color.yellow);                // 土形 3
                      g.fillRect(x,y,75,25);
					  g.fillRect(x+25,y+25,25,25);
					  g.setColor(Color.white);
					  for(int i = x; i < x + 75; i += 25) 
					      for(int j = y; j < y + 50; j += 25)
                              g.drawRect(i,j,25,25);
	   
   }
   public void shape13_1(Graphics g, int x, int y) {          // 畫土形 4 核心函式
	   
	                     g.setColor(Color.yellow);            // 土形 4
                      g.fillRect(x,y+25,25,25);
					  g.fillRect(x+25,y,25,75);
					  g.setColor(Color.white);
					  for(int i = x; i < x + 50; i += 25) 
					      for(int j = y; j < y + 75; j += 25)
                              g.drawRect(i,j,25,25);
	   
   }
   public void shape20_1(Graphics g, int x, int y) {          // 畫小正方形核心函式
	   
	                  g.setColor(Color.gray);                 // 小正方形                    畫出圖形後, 不跳出
                            g.fillRect(x,y,25,25);
                            g.setColor(Color.white);
                            g.drawRect(x,y,25,25);
	   
   }
   public void shape30_1(Graphics g, int x, int y) {         // 畫正方形核心函式
	     
		               g.setColor(Color.red);                // 正方形
                      g.fillRect(x,y,50,50);
                      g.setColor(Color.white);
                      for(int i = x; i < x + 50; i += 25)
                          for(int j = y; j < y + 50; j += 25)
                              g.drawRect(i,j,25,25);
		 
   }
   public void shape40_1(Graphics g, int x, int y) {        // 畫長方形核心函式
	   
	                  g.setColor(Color.orange);             // 長方形
                      g.fillRect(x,y,100,25);
                      g.setColor(Color.white);
                      for(int i = x; i < x + 100; i += 25)
                          g.drawRect(i,y,25,25);
	   
   }
   public void shape41_1(Graphics g, int x, int y) {        // 畫長方形 2 核心函式
	                  
                         g.setColor(Color.orange);          // 長方形 2
                      g.fillRect(x,y,25,100);
                      g.setColor(Color.white);
                      for(int j = y; j < y + 100; j += 25)
                          g.drawRect(x,j,25,25);
					  
   }


	  /***************************************
	  *                                      *
	  *          清除修補核心程式區          *
	  *                                      *
      ****************************************/

   public void shape00_fix_1(Graphics g, int x, int y) {          // 左 Z 形 清除修補核心程式
                        if(y != 100) {
                               g.clearRect(x,y-25,75,25);         // 修補左 Z 形下移
                               g.setColor(Color.white);
                               g.fillRect(x,y-25,75,25);
                               
                               g.clearRect(x+75,y,25,25);         // 修補左 Z 形下移
                               g.setColor(Color.white);
                               g.fillRect(x+75,y,25,25);
                               
						    }
						    
						       g.clearRect(x-25,y,25,25);         // 修補右移
						       g.setColor(Color.white);
						       g.fillRect(x-25,y,25,25);
                               
						       g.clearRect(x,y+25,25,25);         // 修補右移
						       g.setColor(Color.white);
						       g.fillRect(x,y+25,25,25);
                               
							g.clearRect(x+75,y,25,25);            // 修補左移
						    g.setColor(Color.white);
						    g.fillRect(x+75,y,25,25);
                            
						    g.clearRect(x+100,y+25,25,25);        // 修補左移
						    g.setColor(Color.white);
						    g.fillRect(x+100,y+25,25,25);
                            							
							g.clearRect(x,y+25,25,25);            // 修補上移
                            g.setColor(Color.white);
                            g.fillRect(x,y+25,25,25);
                            
                            g.clearRect(x+25,y+50,75,25);         // 修補上移
                            g.setColor(Color.white);
                            g.fillRect(x+25,y+50,75,25);
                            
   }

   public void shape10_fix_1(Graphics g, int x, int y) {         // 土形 清除修補核心程式
	                     if(y != 100) {
                         g.clearRect(x,y-25,75,75);              // 修補土形下移
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,75,75);
                         
						 }
						 
						 g.clearRect(x,y,25,25);                 // 修補右移
						 g.setColor(Color.white);
						 g.fillRect(x,y,25,25);
						 
						 g.clearRect(x-25,y+25,25,25);           // 修補右移
						 g.setColor(Color.white);
						 g.fillRect(x-25,y+25,25,25);
						 
                         g.clearRect(x+50,y,25,25);              // 修補左移
						 g.setColor(Color.white);
						 g.fillRect(x+50,y,25,25);
						 
						 g.clearRect(x+75,y+25,25,25);           // 修補左移
						 g.setColor(Color.white);
						 g.fillRect(x+75,y+25,25,25);
						 						 
						 g.clearRect(x,y+50,75,25);              // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,75,25);
                         
   }

   public void shape20_fix_1(Graphics g, int x, int y) {        // 小正方形 清除修補核心程式
                         if(y != 100) {
						 g.clearRect(x,y-25,25,25);             // 修補小正方形下移
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,25,25);
                         
						 g.clearRect(x,y-50,25,25);             // 修補小正方形下移
                            g.setColor(Color.white);
                            g.fillRect(x,y-50,25,25);
                            
						 }
						 g.clearRect(x-25,y,25,25);            // 修補右移
						 g.setColor(Color.white);
						 g.fillRect(x-25,y,25,25);
						 					
						 g.clearRect(x+25,y,25,25);            // 修補左移
						 g.setColor(Color.white);
						 g.fillRect(x+25,y,25,25);
						 					
						 g.clearRect(x,y+25,25,25);            // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x,y+25,25,25);
                            
   }	   

   public void shape30_fix_1(Graphics g, int x, int y) {       // 正方形 清除修補核心程式
	                     if(y != 100) {
                         g.clearRect(x,y-25,50,25);            // 修補正方形下移
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,50,25);
                         
						 }
						 
                         g.clearRect(x-25,y,25,50);           // 修補右移
                         g.setColor(Color.white);
                         g.fillRect(x-25,y,25,50);
                         
						 g.clearRect(x+50,y,25,50);           // 修補左移
                         g.setColor(Color.white);
                         g.fillRect(x+50,y,25,50);
                         						  
						 g.clearRect(x,y+50,50,25);           // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,50,25);
                         						  
   }

   public void shape40_fix_1(Graphics g, int x, int y) {      // 長方形 清除修補核心程式
	                     
						 if(y != 100) {
                         g.clearRect(x,y-25,100,25);          // 修補長方形下移
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,100,25);
                         
						 }
						 
						 g.clearRect(x-25,y,25,25);           // 修補右移
						 g.setColor(Color.white);
						 g.fillRect(x-25,y,25,25);
						 
						 g.clearRect(x+100,y,25,25);          // 修補左移
						 g.setColor(Color.white);
						 g.fillRect(x+100,y,25,25);
						 
						 g.clearRect(x,y+25,100,25);          // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x,y+25,100,25);
                         
							  
   }

   public void shape01_fix_1(Graphics g, int x, int y) {      // 左 Z 形 2 清除修補核心程式
	                     if(y != 100) {
                            g.clearRect(x+25,y-25,25,25);     // 修補左 Z 形 2 下移
                            g.setColor(Color.white);
                            g.fillRect(x+25,y-25,25,25);
                            
						 }
						 
                         g.clearRect(x-25,y+25,25,75);        // 修補右移
                         g.setColor(Color.white);
                         g.fillRect(x-25,y+25,25,75);
                         
                         g.clearRect(x,y,25,25);              // 修補右移
                         g.setColor(Color.white);
                         g.fillRect(x,y,25,25);
                         
						 
						 g.clearRect(x+25,y,25,25);           // 修補左移
                         g.setColor(Color.white);
                         g.fillRect(x+25,y,25,25);
                         
                         g.clearRect(x+50,y,25,75);           // 修補左移
                         g.setColor(Color.white);
                         g.fillRect(x+50,y,25,75);
                         						 
						 g.clearRect(x+25,y+75,25,25);        // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x+25,y+75,25,25);
                         
						 g.clearRect(x,y+100,25,25);          // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x,y+100,25,25);
                         
   }

   public void shape11_fix_1(Graphics g, int x, int y) {       // 土形 2 清除修補核心程式
	                     
						 if(y != 100) {
                            g.clearRect(x,y-25,25,25);         // 修補土形 2 下移
                            g.setColor(Color.white);
                            g.fillRect(x,y-25,25,25);
                            
                            g.clearRect(x+25,y,25,25);         // 修補土形 2 下移
                            g.setColor(Color.white);
                            g.fillRect(x+25,y,25,25);
                            
						 }
						 
                         g.clearRect(x-25,y,25,75);           // 修補右移						 
						 g.setColor(Color.white);
						 g.fillRect(x-25,y,25,75);
                         						
                         g.clearRect(x+25,y,25,25);           // 修補左移						 
						 g.setColor(Color.white);
						 g.fillRect(x+25,y,25,25);
                         
                         g.clearRect(x+50,y+25,25,25);        // 修補左移						 
						 g.setColor(Color.white);
						 g.fillRect(x+50,y+25,25,25);
                         
						 g.clearRect(x+25,y+50,25,25);        // 修補左移						 
						 g.setColor(Color.white);
						 g.fillRect(x+25,y+50,25,25);
                         						 
						 g.clearRect(x,y+75,25,25);           // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x,y+75,25,25);
                         
                         g.clearRect(x+25,y+50,25,25);        // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x+25,y+50,25,25);
                         
   }

   public void shape12_fix_1(Graphics g, int x, int y) {       // 土形 3 清除修補核心程式
	   
	                     if(y != 100) {
						    g.clearRect(x,y-25,75,75);         // 修補土形 3 下移
                            g.setColor(Color.white);
                            g.fillRect(x,y-25,75,75);
                            
							}
							
                            g.clearRect(x-25,y,25,25);         // 修補右移
                            g.setColor(Color.white);
                            g.fillRect(x-25,y,25,25);
                            
                            g.clearRect(x,y-25,25,25);         // 修補右移
                            g.setColor(Color.white);
                            g.fillRect(x,y-25,25,25);
                            							
							g.clearRect(x+75,y,25,25);         // 修補左移
                            g.setColor(Color.white);
                            g.fillRect(x+75,y,25,25);
                            
                            g.clearRect(x+50,y+25,25,25);      // 修補左移
                            g.setColor(Color.white);
                            g.fillRect(x+50,y+25,25,25);
                            							
							g.clearRect(x,y+25,25,25);         // 修補上移
                            g.setColor(Color.white);
                            g.fillRect(x,y+25,25,25);
                            
						    g.clearRect(x+25,y+50,25,25);      // 修補上移
                            g.setColor(Color.white);
                            g.fillRect(x+25,y+50,25,25);
                            
						    g.clearRect(x+50,y+25,25,25);      // 修補上移
                            g.setColor(Color.white);
                            g.fillRect(x+50,y+25,25,25);
                            	   
   }

   public void shape13_fix_1(Graphics g, int x, int y) {       // 土形 4 清除修補核心程式
	   	                 
						 if(y != 100) {
                            g.clearRect(x+25,y-25,25,25);      // 修補土形 4 下移
                            g.setColor(Color.white);
                            g.fillRect(x+25,y-25,25,25);
                            
						    g.clearRect(x,y,25,25);            // 修補土形 4 下移
                            g.setColor(Color.white);
                            g.fillRect(x,y,25,25);
                            
						 }
						 
						 g.clearRect(x-25,y+25,25,25);         // 修補右移
                         g.setColor(Color.white);
                         g.fillRect(x-25,y+25,25,25);
                         
						 g.clearRect(x,y,25,25);               // 修補右移
                         g.setColor(Color.white);
                         g.fillRect(x,y,25,25);
                         
						 g.clearRect(x,y+50,25,25);            // 修補右移
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,25,25);
                         
						 g.clearRect(x+50,y,25,75);            // 修補左移
                         g.setColor(Color.white);
                         g.fillRect(x+50,y,25,75);
                         
                         g.clearRect(x+25,y+75,25,25);         // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x+25,y+75,25,25);
                         
						 g.clearRect(x,y+50,25,25);            // 修補上移
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,25,25);
                         
   }

   public void shape41_fix_1(Graphics g, int x, int y) {       // 長方形 2 清除修補核心程式
	                     
						    if(y != 100) {
                               g.clearRect(x,y-25,25,25);      // 修補長方形 2 下移
                               g.setColor(Color.white);
                               g.fillRect(x,y-25,25,25);     
                               
							}
							
                            g.clearRect(x-25,y,25,100);        // 修補右移
                            g.setColor(Color.white);
                            g.fillRect(x-25,y,25,100);
                            
						    g.clearRect(x+25,y,25,100);        // 修補左移
                            g.setColor(Color.white);
                            g.fillRect(x+25,y,25,100);
                            
						    g.clearRect(x,y+100,25,25);        // 修補上移
                            g.setColor(Color.white);
                            g.fillRect(x,y+100,25,25);
                            
   }
	  /***************************************
	  *                                      *
	  *             Windows 事件             *
	  *                                      *
      ****************************************/   
   public void windowClosing(WindowEvent e)
   {
      System.exit(0); //frm.dispose();
   }
   public void windowClosed(WindowEvent e) {}
   public void windowDeactivated(WindowEvent e) {}
   public void windowActivated(WindowEvent e) {}
   public void windowDeiconified(WindowEvent e) {}
   public void windowIconified(WindowEvent e) {}
   public void windowOpened(WindowEvent e) {}

   public void keyPressed(KeyEvent e)                           // 決定 前進 , 後退 , 左移, 右移 x,y 的位置(移動規則)
   {
	  /***************************************
	  *                                      *
	  *             上鍵上移事件             *
	  *                                      *
      ****************************************/
      if(e.getKeyCode() == KeyEvent.VK_UP && y >= 100) {
         if(y == 100) {
            y = 100;
         } else {
            y -= 25;
         }
		 paint(canvas.getGraphics());
      }
	  /***************************************
	  *                                      *
	  *             下鍵下移事件             *
	  *                                      *
      ****************************************/
      if(e.getKeyCode() == KeyEvent.VK_DOWN && y <= 625) {
		 int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 0) {      // 左 Z 形
		        if(y >= 575) {         // 針對 左 Z 形 再增加一限制條件 , y > 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py+2 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1 || checked[py+2][px+3] != -1)) {   // 如果下邊不為 -1
						  return;
                }
         }
		 if(index == 1) {      // 土形
		        if(y >= 575) {         // 針對 土形 再增加一限制條件 , y > 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py+2 < 21 && px+2 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1)) {   // 如果下邊不為 -1
						  return;
                }
         }
		 if(index == 2) {      // 小正方形
		        if(y >= 600) {         // 針對 小正方形 再增加一限制條件 , y > 600 時, 在陣列外會檢查不到 !!!
                          return;
                }
		        if(py+1 < 21 && checked[py+1][px] != -1) {   // 如果下邊不為 -1
						  return;
                }
				
		 }
		 if(index == 3) {      // 正方形
		        if(y >= 575) {         // 針對 正方形 再增加一限制條件 , y > 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py+2 < 21 && px+1 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1)) {   // 如果下邊不為 -1
						  return;
                }
         }
		 if(index == 4) {      // 長方形
		        if(y >= 600) {         // 針對 長方形 再增加一限制條件 , y > 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py+1 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+1] != -1 || checked[py+1][px+2] != -1 || checked[py+1][px+3] != -1)) {   // 如果下邊不為 -1
						  return;
                }
		 }
		 if(index == 5) {      // 左 Z 形 2
		        if(y >= 525) {         // 針對 左 Z 形 2 再增加一限制條件 , y > 525 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py+4 < 21 && px+1 < 13 && (checked[py+4][px] != -1 || checked[py+3][px+1] != -1)) {   // 如果下邊不為 -1
						  return;
                }
		 }
		 if(index == 6) {        // 土形 2
		        if(y >= 550) {         // 針對 土形 2 再增加一限制條件 , y > 550 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py+3 < 21 && px+1 < 13 && (checked[py+3][px] != -1 || checked[py+2][px+1] != -1)) {   // 如果下邊不為 -1
						  return;
                }
		 }
		 if(index == 7) {        // 土形 3
		        if(y >= 575) {         // 針對 土形 3 再增加一限制條件 , y > 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py+2 < 21 && px+2 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+1] != -1)) {   // 如果下邊不為 -1
						  return;
                }
		 }
		 if(index == 8) {        // 土形 4
                if(y >= 550) {         // 針對 土形 4 再增加一限制條件 , y > 550 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py+3 < 21 && px+1 < 13 && (checked[py+2][px] != -1 || checked[py+3][px+1]!= -1)) {   // 如果下邊不為 -1
						  return;
                }
		 }
		 if(index == 9) {      // 長方形 2
                 if(y >= 525) {         // 針對 長方形 2 再增加一限制條件 , y > 525 時, 在陣列外會檢查不到 !!!
						   return;
                 }
		          if(py+4 < 21 && (checked[py+4][px] != -1)) {
						    return;                       // 如果下邊不為 -1
                  }
		 }
	     if(y >= 625) {
            y = 600;
         } else {
            y += 25;
         }
		 paint(this.getGraphics());
      }
	  /***************************************
	  *                                      *
	  *             左鍵左移事件             *
	  *                                      *
      ****************************************/
      else if(e.getKeyCode() == KeyEvent.VK_LEFT && x >= 155) {
		 int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 0) {      // 左 Z 形
		        if(y >= 600) {         // 針對 左 Z 形 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && px > -1 && py+1 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px] != -1)) {   // 如果左邊不為 -1
						  return;
                }
         }
		 if(index == 1) {      // 土形
		        if(y >= 600) {         // 針對 土形 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px-1 > -1 && (checked[py+1][px-1] != -1 || checked[py][px] != -1)) {   // 如果左邊不為 -1
						  return;
                }
         }
		 if(index == 2) {      // 小正方形
		        if(y >= 625) {         // 針對 小正方形 再增加一限制條件 , y == 625 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py < 21 && py > -1 && px-1 > -1 && checked[py][px-1] != -1) {   // 如果左邊不為 -1
						  return;
                }
		 }
		 if(index == 3) {      // 正方形
		        if(y >= 600) {         // 針對 正方形 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px-1] != -1)) {   // 如果左邊不為 -1
						  return;
                }
         }
		 if(index == 4) {      // 長方形
		        if(y >= 625) {         // 針對 長方形 再增加一限制條件 , y == 625 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && px-1 > -1 && checked[py][px-1] != -1) {   // 如果左邊不為 -1
						  return;
                }
		 }
		 if(index == 5) {      // 左 Z 形 2
		        if(y >= 550) {         // 針對 左 Z 形 2 再增加一限制條件 , y == 550 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && py+3 < 21 && px-1 > -1 && (checked[py+1][px-1] != -1 || checked[py+2][px-1] != -1 || checked[py+3][px-1] != -1 || checked[py][px] != -1)) {   // 如果左邊不為 -1
						  return;
                }
		 } 
		 if(index == 6) {        // 土形 2
		        if(y >= 575) {         // 針對 土形 2 再增加一限制條件 , y == 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px-1] != -1 || checked[py+2][px-1] != -1)) {   // 如果左邊不為 -1
						  return;
                }
		 }
		 if(index == 7) {        // 土形 3
		        if(y >= 600) {         // 針對 土形 3 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && py+1 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px] != -1)) {   // 如果左邊不為 -1
						  return;
                }
		 }
		 if(index == 8) {        // 土形 4
		        if(y >= 575) {         // 針對 土形 4 再增加一限制條件 , y == 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px-1 > -1 && (checked[py][px] != -1 || checked[py+1][px-1] != -1 || checked[py+2][px] != -1 )) {   // 如果左邊不為 -1
						  return;
                }
		 }
		 if(index == 9) {      // 長方形 2
		          if(y >= 550) {         // 針對 長方形 2 再增加一限制條件 , y == 550 時, 在陣列外會檢查不到 !!!
						    return;
                  }
		          if(py > -1 && px-1 > -1 && py+3 < 21 && (checked[py][px-1] != -1 || checked[py+1][px-1] != -1 || checked[py+2][px-1] != -1 || checked[py+3][px-1] != -1)) {
						    return;                       // 如果左邊不為 -1
                  }
		 }
     	 if(x == 155) {
            x = 155;
         } else {
            x -= 25;
         }
		 paint(getGraphics());
      }
	  /***************************************
	  *                                      *
	  *             右鍵右移事件             *
	  *                                      *
      ****************************************/
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 0 || index == 4) && x <= 380) {     // 左 z 形 , 長方形 向右規則
	     int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 0) {      // 左 Z 形
		        if(y >= 600) {         // 針對 左 Z 形 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px+4 < 13 && (checked[py][px+3] != -1 || checked[py+1][px+4] != -1)) {   // 如果右邊不為 -1
						  return;
                }
         }
	     if(index == 4) {      // 長方形
		        if(y >= 625) {         // 針對 長方形 再增加一限制條件 , y == 625 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && py < 21 && px+4 < 13 && checked[py][px+4] != -1) {   // 如果右邊不為 -1
						  return;
                }
		 }
         if(x == 380) {
            x = 380;
         } else {
            x += 25;
         }
	     paint(canvas.getGraphics());
      }
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 1 || index == 7) && x <= 405) {     // 土形 , 土形 3 向右規則
	     int py = (y-100)/25;
		 int px = (x-155)/25;
	     if(index == 1) {      // 土形
		        if(y >= 600) {         // 針對 土形 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py+1 < 21 && px+3 < 13 && py > -1 && (checked[py+1][px+3] != -1 || checked[py][px+2] != -1)) {   // 如果右邊不為 -1
						  return;
                }
         }
		 if(index == 7) {        // 土形 3
		        if(y >= 600) {         // 針對 土形 3 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && px+3 < 13 && py+1 < 21 && (checked[py][px+3] != -1 || checked[py+1][px+2] != -1)) {   // 如果右邊不為 -1
						  return;
                }
		 }
         if(x == 405) {
            x = 405;
         } else {
            x += 25;
         }
	     paint(canvas.getGraphics());
      }
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 3 || index == 5 || index == 6 || index == 8) && x <= 430) {     // 左 z 形 2 , 正方形 , 土形 2 , 土形 4 向右規則
	     int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 3) {      // 正方形
		        if(y >= 600) {         // 針對 正方形 再增加一限制條件 , y == 600 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px+2 < 13 && (checked[py][px+2] != -1 || checked[py+1][px+2] != -1)) {   // 如果右邊不為 -1
						  return;
                }
         }
	     if(index == 5) {      // 左 Z 形 2
		        if(y >= 550) {         // 針對 左 Z 形 2 再增加一限制條件 , y == 550 時, 在陣列外會檢查不到 !!!
						  return;
                }
	            if(py > -1 && py+3 < 21 && px+2 < 13 && (checked[py][px+2] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+2] != -1 || checked[py+3][px+1] != -1)) {   // 如果右邊不為 -1
						  return;
                }
         }
		 if(index == 6) {        // 土形 2
		        if(y >= 575) {         // 針對 土形 2 再增加一限制條件 , y == 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px+2 < 13 && (checked[py][px+1] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+1] != -1)) {   // 如果右邊不為 -1
						  return;
                }
		 }
		 if(index == 8) {        // 土形 4
		        if(y >= 575) {         // 針對 土形 4 再增加一限制條件 , y == 575 時, 在陣列外會檢查不到 !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px+2 < 13 && (checked[py][px+2] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+2] != -1 )) {   // 如果右邊不為 -1
						  return;
                }
		 }
         if(x == 430) {
            x = 430;
         } else {
            x += 25;
         }
	     paint(canvas.getGraphics());
      }
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 2 || index == 9) && x <= 455) {      // 小正方形 , 長方形 2 向右規則
	        int py = (y-100)/25;
		    int px = (x-155)/25;
	        if(index == 2) {      // 小正方形
		          if(y >= 625) {         // 針對 小正方形 再增加一限制條件 , y == 625 時, 在陣列外會檢查不到 !!!
						    return;
                  }
		          if(py > -1 && py < 21 && px+1 < 13 && checked[py][px+1] != -1) {   // 如果右邊不為 -1
						    return;
                  }
		    }
            if(index == 9) {      // 長方形 2
		          if(y >= 550) {         // 針對 長方形 2 再增加一限制條件 , y == 550 時, 在陣列外會檢查不到 !!!
						    return;
                  }
		          if(py > -1 && px+1 < 13 && py+3 < 21 && (checked[py][px+1] != -1 || checked[py+1][px+1] != -1 || checked[py+2][px+1] != -1 || checked[py+3][px+1] != -1)) {
						    return;                           // 如果右邊不為 -1
                  }
		    }			
	        if(x == 455) {
               x = 455;
            } else {
               x += 25;
            }
			paint(canvas.getGraphics());
      }
	  /***************************************
	  *                                      *
	  *            空白鍵轉換事件            *
	  *                                      *
      ****************************************/
      else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 0) {                   // 按空白鍵轉換圖形 ( 左 Z 形 轉 左 Z 形 2 )
	     int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(x > 430) {
			 return;
		 }
		 if(py > -1 && px > -1 && py+3 < 21 && px+1 < 13 && (checked[py+1][px] != -1 || checked[py+2][px] != -1 || checked[py+3][px] != -1 
		             || checked[py][px+1] != -1 || checked[py+1][px+1] != -1 || checked[py+2][px+1] != -1)) {
			 return;
		 }
         index = 5;
	     paint(canvas.getGraphics());
      }
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 5) {                   // 按空白鍵轉換圖形 ( 左 Z 形 2 轉 左 Z 形 )
		 int py = (y-100)/25;
		 int px = (x-155)/25;
         if(x > 380) {
			 return;
		 } 
		 if(py > -1 && px > -1 && py+1 < 21 && px+3 < 13 && (checked[py][px] != -1 || checked[py][px+1] != -1 || checked[py][px+2] != -1 
		              || checked[py+1][px+1] != -1 || checked[py+1][px+2] != -1 || checked[py+1][px+3] != -1)) {
			 return;
		 }
         index = 0;
	     paint(canvas.getGraphics());
      }
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 1) {                   // 按空白鍵轉換圖形 ( 土形 轉 土形 2 )
		 int py = (y-100)/25;
		 int px = (x-155)/25;
         if(x > 430) {
			 return;
		 }
		 if(py > -1 && px > -1 && py+2 < 21 && px+1 < 13 && (checked[py][px] != -1 || checked[py+1][px] != -1 || checked[py+2][px] != -1
		              || checked[py+1][px+1] != -1)) {
			 return;
		 }
         index = 6;
	     paint(canvas.getGraphics());
      }
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 6) {                   // 按空白鍵轉換圖形 ( 土形 2 轉 土形 3 )
		 int py = (y-100)/25;
		 int px = (x-155)/25;
         if(x > 405) {
			 return;
		 }
		 if(py > -1 && px > -1 && py+1 < 21 && px+2 < 13 && (checked[py][px] != -1 || checked[py][px+1] != -1 || checked[py][px+2] != -1 
		              || checked[py+1][px+1] != -1)) {
			 return;
		 }
         index = 7;
	     paint(canvas.getGraphics());
      }
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 7) {                   // 按空白鍵轉換圖形 ( 土形 3 轉 土形 4 )
		 int py = (y-100)/25;
		 int px = (x-155)/25;
         if(x > 430) {
			 return;
		 }
		 if(py > -1 && px > -1 && py+2 < 21 && px+1 < 13 && (checked[py+1][px] != -1 || checked[py][px+1]!= -1 || checked[py+1][px+1] != -1 || checked[py+2][px+1] != -1)) {
			 return;
		 }
         index = 8;
	     paint(canvas.getGraphics());
      }
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 8) {                   // 按空白鍵轉換圖形 ( 土形 4 轉 土形 )
		 int py = (y-100)/25;
		 int px = (x-155)/25;
         if(x > 405) {
			 return;
		 }
		 if(py > -1 && px > -1 && py+1 < 21 && px+2 < 13 && (checked[py][px+1] != -1 || checked[py+1][px] != -1 || checked[py+1][px+1] != -1 || checked[py+1][px+2] != -1)) {
			 return;
		 }
         index = 1;
	     paint(canvas.getGraphics());
      }
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 9) {                   // 按空白鍵轉換圖形 ( 長方形 2 轉 長方形 )
		 int py = (y-100)/25;
		 int px = (x-155)/25;
         if(x > 380) {
			 return;
		 }
		 if(py > -1 && px > -1 && px+3 < 13 && (checked[py][px] != -1 || checked[py][px+1] != -1 || checked[py][px+2] != -1 || checked[py][px+3] != -1)) {
			 return;
		 }
         index = 4;
	     paint(canvas.getGraphics());
      }
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 4) {                   // 按空白鍵轉換圖形 ( 長方形 轉 長方形 2 )
	     int py = (y-100)/25;
		 int px = (x-155)/25;
         if(x > 455) {
			 return;
		 }
		 if(py > -1 && px > -1 && py+3 < 21 && (checked[py][px] != -1 || checked[py+1][px] != -1 || checked[py+2][px] != -1 || checked[py+3][px] != -1)) {
			 return;
		 }
         index = 9;
	     paint(canvas.getGraphics());
      }
      else {}
      
   }
   public void keyReleased(KeyEvent e) {} 
   public void keyTyped(KeyEvent e) {}

     
}


