/***************************************************************************************************************************************
 *	                                  �ۼg�Xù������C�� by Alex Chen  2019/6/17                                                       *
 *	                                                                                                                                   *
 *  ���k :                                                                                                                             *
 *                                                                                                                                     *
 *  �D�n�Q�� AWT �e�X�U�عϧΡA�ðt�X����ƥ��X����y���ʤ��ഫ�ϧΪ��ĪG�A���]���ϥΦh������H�h�u�Y�ɳB�z�U�بƥ�A                *
 *  �P�ɥH�x�} 21 X 13 �ˬd���x�s�C�Ӧ�m����T�A-1 ���i�q��A0 ~ 9 ���U�عϧΩT�w�᪺�x�s��T�A -2 ������T�w��s���@�C�ɪ��Ȧs�Ʀr�A *
 *  ø�ϳ̤p���h���e�B���U 25 �I������ΡA�åH���զ��U�عϧΡC                                                                       *
 *                                                                                                                                     *
 *                                               -1 : ������ , �i�q��                                                                  *
 *                                               -2 : ����s���@�C�ɼȦs�Ʀr                                                           *
 *                                                0 : �� Z �� ����                                                                     *
 *	                                              1 : �g�� ����                                                                        *
 *			                                      2 : �p����� ����                                                                    *
 *			                                      3 : ����� ����                                                                      *
 *                                                4 : ����� ����                                                                      *
 *			                                      5 : �� Z �� 2 ����                                                                   *
 *			                                      6 : �g�� 2 ����                                                                      *
 *			                                      7 : �g�� 3 ����                                                                      *
 *			                                      8 : �g�� 4 ����                                                                      *
 *			                                      9 : ����� 2 ����                                                                    *
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
	  *   �T�w����C����@�δ��լO�_�s���@�C���O   *
	  *                                            *
      **********************************************/
class color_Thread extends Tetris6 implements Runnable{       // ���@�w�T�w��m���C��
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
					count++;                                   //    �w����p��
			   
			   if(count == 13) {                               //    �ˬd�O�_�s���@�C (�� 13 ��)
					for(int k = 0; k < 13; k++) {
						checked[i][k] = -2;                    //    �C����� -2 (�w���γs���@�C , ��զ�)
					}
					for(int l = i; l > 0; l--) {               //     ���̫�@�C, �̫�@�C����
					    for(int m = 0; m < 13; m++) {
						    checked[l][m] = checked[l-1][m];   //     �A��W�@�C�Ʀr  
					    }
					}
					
					score += 500;                              //     �C���h�@�C, �[ 500 ��
					line += 1;                                 //     ���h�C�ƼW�[ 1
					Music3();                                  //     ������h���� !!
                }
            }
        }			
				
		for(int i = 0; i < 21; i++) {
			
			for(int j = 0; j < 13; j++) {
			   		
               if(checked[i][j] == 0) {             // �� Z �� �Ŧ�
				   
				   fillCellColor(g, i, j, Color.blue); 
				   
		       } else if(checked[i][j] == 1) {      // �g�� ����
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 2) {      // �p����� �Ǧ�
			   
				   fillCellColor(g, i, j, Color.gray); 
				   
		       } else if(checked[i][j] == 3) {      // ����� ����
			   
				   fillCellColor(g, i, j, Color.red); 
				   
		       } else if(checked[i][j] == 4) {      // ����� ���
			   
				   fillCellColor(g, i, j, Color.orange); 
				   
		       } else if(checked[i][j] == 5) {      // �� Z �� 2 �Ŧ�
			   
				   fillCellColor(g, i, j, Color.blue); 
				   
		       } else if(checked[i][j] == 6) {      // �g�� 2 ����
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 7) {      // �g�� 3 ����
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 8) {      // �g�� 4 ����
			   
				   fillCellColor(g, i, j, Color.yellow); 
				   
		       } else if(checked[i][j] == 9) {      // ����� 2 ���
			   
				   fillCellColor(g, i, j, Color.orange); 
				   
		       } else if(checked[i][j] == -2) {      // ���h������] 2 �զ�
			   
				   fillCellColor(g, i, j, Color.white); 
				   
		       } 
			   
			}
			
		}
		
	  }
	  
		
   }
   void fillCellColor(Graphics g, int i, int j, Color color) {            //            �������C��禡
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
	  *           �~�غ��@�θ�T������O           *
	  *                                            *
      **********************************************/
class frame_Thread extends Tetris6 implements Runnable{           // ���@�~��
    public void run()
   {

      paint(canvas.getGraphics());
         
   }
   
   public void paint(Graphics g) {

      while(true) {
        for(int x=130 ; x<=130; x+=25)                     // �����
            for(int y=100 ; y<=625; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		for(int x=130 ; x<=480; x+=25)                     // �U���
            for(int y=625 ; y<=625; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		for(int x=480 ; x<=480; x+=25)                     // �k���
            for(int y=100 ; y<=625; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		for(int x=130 ; x<=480; x+=25)                     // �W���
            for(int y=75 ; y<=75; y+=25) 
            {
                g.setColor(Color.magenta);
                g.fillRect(x,y,25,25);
                
            }
		str2 = String.valueOf(score);
		str3 = String.valueOf(line);
		String str4 = " LINE COUNT : ";
		g.setFont(new Font("Arial",Font.PLAIN,18));
        g.drawString(str,500,100);                       // ��� SCORE
		g.clearRect(560,110,80,25);                      // �M����ܽd��, �קK?�v
		g.drawString(str2,560,135);
		g.drawString(str4,500,170);                      // ��� LINE COUNT
		g.clearRect(560,180,80,25);                      // �M����ܽd��, �קK?�v
		g.drawString(str3,560,205);
		
		g.setFont(new Font("Arial",Font.BOLD,15));
		g.drawString(" \u2191    UP ",25,540);            // ��ܦV�W�b�Y
		g.drawString(" \u2190   LEFT",25,565);            // ��ܦV���b�Y
		g.drawString(" \u2192   RIGHT ",25,590);          // ��ܦV�k�b�Y
		g.drawString(" \u2193    DOWN ",25,615);          // ��ܦV�U�b�Y
		g.drawString(" SPACE change ",17,640);            // �Ϊť����ܴ��ϧ�
		g.drawRect(15, 625, 60, 20);         
	  }
   }
}
	  /*********************************************
	  *                                            *
	  *       ����e�X����δ��լO�_���\���O       *
	  *                                            *
      **********************************************/
class loop_Thread extends Tetris6 implements Runnable{           // ����e�X���������� 
      
   public void run()
   {
	   
	  Music();                              // ����I������
      update(canvas.getGraphics());
       
   }

   public void paint(Graphics g) {
        		
        for(y = 100; y <= 625; y += 25) {
			
			if(line >= 5) {                                              // �ˬd�O�_�w���h 5 ���s���@�C
				System.out.println(" YOU PASS !!! ");                    // �b����x��ܦ��\�T��
		        g.setColor(Color.green);
	            g.setFont(new Font("Arial",Font.PLAIN,18)); 
                g.drawString(" You PASS !!! ",250,75);                   // �b�����W����ܦ��\�T��
		        g.setFont(new Font("Arial",Font.PLAIN,50));
                g.drawString(" You PASS !!! ",180,250);                  // �b����������ܦ��\�T��
		        Music_Stop();
		        Music_pass();
				break;                                                   // ���\�Y���X�j��
			}
				
		    if(y == 100) {                                        // �p��j��}�l�B
			   
			   index2 = (int)(Math.random()*10);                  // index2 : ���U�@����ܹϧ�
			   index = (int)(queue.poll());                       // index : �q��C�����X�ثe����ܹϧ�
			   queue.offer(index2);                               // index2 ��J��C������� 
			   
			}
			
		    int px = (x - 155) / 25;        // �p���y��
			
		    if(px+1 < 13 && (checked[0][px] != -1 || checked[0][px+1] != -1)) {      // �ˬd�Ĥ@�Ӧ�m�O�_�w���Ψ쳻
					   
					   break;               // ���ΧY���X�j��
			}
			
            try {
               Thread.sleep(300);           // ���� 0.3 ��   (�������U���t��)
            } catch(InterruptedException e) {
               System.out.println(" do something ... ");
            }
            
      
                    if(index == 0) {                              // �M�����e�ϧ�
                         shape00_fix(g);      // �׸ɥ� Z ��
                    }
                    else if(index == 1) {
                         shape10_fix(g);      // �׸ɤg��
                    }
                    else if(index == 2) {
                         shape20_fix(g);      // �׸ɤp�����
                    }
                    else if(index == 3) {
                         shape30_fix(g);      // �׸ɥ����
                    }
                    else if(index == 4) {
                         shape40_fix(g);      // �׸ɪ����
                    }
					else if(index == 5) {
                         shape01_fix(g);      // �׸ɥ� Z �� 2
                    }
					else if(index == 6) {
                         shape11_fix(g);      // �׸ɤg�� 2
                    }
					else if(index == 7) {
                         shape12_fix(g);      // �׸ɤg�� 3
                    }
					else if(index == 8) {
                         shape13_fix(g);      // �׸ɤg�� 4
                    }
					else if(index == 9) {
                         shape41_fix(g);      // �׸ɪ���� 2
                    }
               
            if(y == 625) {        // �O�_�̫�@���u ?

                   y = 100;
                                          
            }
            
               if(index == 0) {                                  // �e�ϧ�
       
                     shape00(g);      // �� Z ��
               }
  
               else if(index == 1) {

                     shape10(g);      // �g��

               }

               else if(index == 2) {     

                     shape20(g);      // �p�����

               }
               else if(index == 3) {

                     shape30(g);      // �����

               }
               else if(index == 4) {

                     shape40(g);      // �����

               }
               else if(index == 5) {

                     shape01(g);      // �� Z �� 2

               }
			   else if(index == 6) {

                     shape11(g);      // �g�� 2

               }
			   else if(index == 7) {

                     shape12(g);      // �g�� 3

               }
			   else if(index == 8) {

                     shape13(g);      // �g�� 4

               }
			   else if(index == 9) {

                     shape41(g);      // ����� 2

               }
			   
            printNext(g);                           // �b����C�L�U������ܹϧ�
        }
		
		if(line < 5) {                              // �p�G�񺡳̫�@����X��, ������ 5 ���s���@�C, ��ܥ��ѰT��
			
		System.out.println(" YOU LOSE !!! ");       // �b����x��ܥ��ѰT��
		g.setColor(Color.red);
	    g.setFont(new Font("Arial",Font.PLAIN,18));
        g.drawString(" You Lose !!! ",250,75);      // �b�����W����ܥ��ѰT��
		g.setFont(new Font("Arial",Font.PLAIN,50));
        g.drawString(" You Lose !!! ",180,250);     // �b����������ܥ��ѰT��
		
		Music_Stop();                               // �����I������
		Music_failed();                             // ���񥢱ѭ���
		
		for(int y=100 ; y<=600; y+=25)              // ���ѫ�,������^�Ǧ⪺���
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
      public void printNext(Graphics g) {                           // ����C�L�U������ܹϧΨ禡    
		int x = 12;
		int y = 180;
		
        g.setColor(Color.red);
		g.setFont(new Font("Arial",Font.ITALIC,22));
        g.drawString(" NEXT ... ",0,150);
		
	    g.setColor(Color.white);
	    g.fillRect(12,180,100,100);

	        if(index2 == 0) {
     	   
               shape00_1(g,x,y);      // �� Z ��
			   
		    }
		    if(index2 == 1) {
				
     	       shape10_1(g,x,y);      // �g��
			   
		    }
		    if(index2 == 2) {
				
     	       shape20_1(g,x,y);      // �p�����
			   
		    }
		    if(index2 == 3) {
				
               shape30_1(g,x,y);      // �����
			   
		    }
		    if(index2 == 4) {
				
     	       shape40_1(g,x,y);      // �����
			   
		    }
		    if(index2 == 5) {
				
     	    shape01_1(g,x,y);         // �� Z �� 2
			
		    }
		    if(index2 == 6) {
				
     	       shape11_1(g,x,y);      // �g�� 2
			   
		    }
		    if(index2 == 7) {
				
     	       shape12_1(g,x,y);      // �g�� 3
			   
		    }
		    if(index2 == 8) {
				
     	       shape13_1(g,x,y);      // �g�� 4
			   
		    }
		    if(index2 == 9) {
				
     	       shape41_1(g,x,y);      // ����� 2
			   
		    }
   } 
}  

	  /*********************************************
	  *                                            *
	  *               Tetris6 �D���O               *
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
   

   static int[][] checked = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       // �ΨӧP�_�O�_�i�q��, ���x�s�Ӧ�m����عϧ��C��
                              {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       // -1 : ������ , �i�q��
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       // -2 : ����s���@�C�ɼȦs�Ʀr
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  0 : �� Z �� ���� 
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  1 : �g�� ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  2 : �p����� ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  3 : ����� ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  4 : ����� ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  5 : �� Z �� 2 ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  6 : �g�� 2 ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  7 : �g�� 3 ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  8 : �g�� 4 ����
							  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},       //  9 : ����� 2 ����
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
	  frm.addWindowListener(canvas);             // �]�w canvas �� Windows Event �ƥ��ť��
      frm.addKeyListener(canvas);                // �]�w canvas �� Key Event �ƥ��ť��
	  
	  queue.offer(index);                        // ��C��J���(�Ĥ@����ܹϧ�)

      frm.setVisible(true);

      loop_Thread loop_thread = new loop_Thread();
      frame_Thread frame_thread = new frame_Thread();	
      color_Thread color_thread = new color_Thread();
	  
      Thread t1 = new Thread(loop_thread);
	  Thread t2 = new Thread(frame_thread);
	  Thread t3 = new Thread(color_thread);
	  
	  t1.start();           // ����e�X���������� 
	  t2.start();           // ���@�~�ذ����
	  t3.start();           // ���@�w�T�w��m���C������
	  
   }

   public void paint(Graphics g)
   {
        for(int x=155 ; x<=455; x+=25)              // ���_��s�I���C�� , �i�קK�ݼv�o��
            for(int y=100 ; y<=600; y+=25) 
            {

                g.setColor(Color.white);
                g.fillRect(x,y,25,25);
                
            }
   }
	  /***************************************
	  *                                      *
	  *              ���ֵ{����              *
	  *                                      *
      ****************************************/   
 void Music(){ //�`�N�Ajava�u�༽��L�l����A�p.wav�o�خ榡                                    // �I������
      try { 
	       File f = new File(".\\background.wav"); //������| 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //�ѪR���| 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //�榱�`�� 
		   
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}
 void Music_Stop(){ //�`�N�Ajava�u�༽��L�l����A�p.wav�o�خ榡                               // �����I�����֭I������ 
      try { 
	       File f = new File(".\\background.wav"); //������| 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //�ѪR���| 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //�榱�`�� 
		   aau.stop(); 
	  } catch (Exception e) { 
		          e.printStackTrace();
	 } 
}
void Music2(){ //�`�N�Ajava�u�༽��L�l����A�p.wav�o�خ榡                                   // �j�����|����  
      try { 
	       File f = new File(".\\stack.wav"); //������| 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //�ѪR���| 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //�榱�`�� 
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
void Music3(){ //�`�N�Ajava�u�༽��L�l����A�p.wav�o�خ榡                                     // �j���s���@�C���h����   
      try { 
	       File f = new File(".\\delete.wav"); //������| 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //�ѪR���| 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //�榱�`�� 
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
void Music_failed(){ //�`�N�Ajava�u�༽��L�l����A�p.wav�o�خ榡                                // ���ѭ��� 
      try { 
	       File f = new File(".\\fail.wav"); //������| 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //�ѪR���| 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //�榱�`�� 
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
void Music_pass(){ //�`�N�Ajava�u�༽��L�l����A�p.wav�o�خ榡                                  // �L������  
      try { 
	       File f = new File(".\\pass.wav"); //������| 
		   URI uri = f.toURI(); 
		   URL url = uri.toURL(); //�ѪR���| 
		   AudioClip aau;
		   aau = Applet.newAudioClip(url); 
		   aau.loop(); //�榱�`�� 
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
	  *         �� Z �� ø�ϳW�h�{��         *
	  *                                      *
      ****************************************/
   public void shape00(Graphics g) {
   
					  int py = (y-100)/25;                        // �p��C�y��
					  int px = (x-155)/25;                        // �p���y��

                      if(x >= 380) {                              // �� Z �� �k����ɭ���
		                  x = 380;
		              } 
                      if(y >= 600) {                              // �� Z �� �U����ɭ���
		                  y = 575;
		              }                      
					  if(py > -1 && px > -1 && py+2 < 21 && px+3 < 13 && (checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1       // ���եثe�w���m�U��
					                                                       || checked[py+2][px+3] != -1 || checked[py+1][px] != -1)) { // ���L��L�ϧ�
					 
                         shape00_1(g,x,y);                        // �I�s�e�� Z �ή֤ߨ禡	
						  
						 checked[py][px] = 0;                     // �U�観��L�ϧΫh�� �� Z �� �N���X "0"
					     checked[py][px+1] = 0;
						 checked[py][px+2] = 0;
						 checked[py+1][px+1] = 0;
						 checked[py+1][px+2] = 0;
						 checked[py+1][px+3] = 0;
							 
						 y = 75;                                  // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
						
						 Music2();                                // ����j�����|����
						 return;
					  }
  
                      shape00_1(g,x,y);                           // �I�s�e�� Z �ή֤ߨ禡

   }
	  /***************************************
	  *                                      *
	  *      �� Z �� ���ʮɲM���׸ɵ{��      *
	  *                                      *
      ****************************************/
   public void shape00_fix(Graphics g) {
						 int py = (y-100)/25;                     // �p��C�y��
					     int px = (x-155)/25;                     // �p���y��	
					  if(y == 600 && py-1 > -1 && px+3 < 13) {                           // �O�_��F�U�ɭ� ?
						     							 
							 //shape00_1(g,x,y);                        // �I�s�e�� Z �ή֤ߨ禡
							 
							 checked[py-1][px] = 0;                 // �O���� , �b �� Z �� �y�Ц�m��N���X "0"
							 checked[py-1][px+1] = 0;
							 checked[py-1][px+2] = 0;
						     checked[py][px+1] = 0;
							 checked[py][px+2] = 0;
							 checked[py][px+3] = 0;
							 y = 100;                             // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                             index2 = (int)(Math.random() * 10);  // �H������ϧ�
						     index = (int)queue.poll();
						     queue.offer(index2);
							 
                             Music2();                            // ����j�����|����
							 return;
                      }    
                         
					 
						 if(py > -1 && px > -1 && py+2 < 21 && px+3 < 13 && (checked[py+2][px+1] != -1 // �ˬd�w���m�U�����L����ϧ�
						                 || checked[py+2][px+2] != -1 || checked[py+2][px+3] != -1)) {
						 
						    shape00_fix_1(g,x,y);                 // �I�s �� Z �� �M���׸ɮ֤ߵ{��
												 
						    return;
						 }
						 
                            shape00_fix_1(g,x,y);                 // �I�s �� Z �� �M���׸ɮ֤ߵ{��
						 
   }
	  /***************************************
	  *                                      *
	  *         �� Z �� 2 ø�ϳW�h�{��       *
	  *                                      *
      ****************************************/
   public void shape01(Graphics g) {
   
   					  int py = (y-100)/25;                        // �p��C�y��
					  int px = (x-155)/25;                        // �p���y��	
                      
                      if(x >= 430) {                              // �� Z �� 2 �k����ɭ���
		                  x = 430;
		              }
                      if(y >= 550) {                              // �� Z �� 2 �U����ɭ���
		                  y = 525;
		              }
					  
					  if(py > -1 && px > -1 && py+4 < 21 &&  px+1 < 13 && (checked[py+4][px] != -1 || checked[py+3][px+1] != -1)) { // ���եثe�w���m�U��
						                                                                                                            // ���L��L�ϧ�
                         shape01_1(g,x,y);                        // �I�s�e�� Z �� 2 �֤ߨ禡
							  
						 checked[py][px+1] = 5;                   // �U�観��L�ϧΫh�� �� Z �� 2 �N���X "5"
						 checked[py+1][px] = 5;
						 checked[py+1][px+1] = 5;
						 checked[py+2][px] = 5;
						 checked[py+2][px+1] = 5;
						 checked[py+3][px] = 5; 
							 
						 y = 75;                                  // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
                         
                         Music2();                                // ����j�����|����						 
						 return;
						 
					  }

                         shape01_1(g,x,y);                        // �I�s�e�� Z �� 2 �֤ߨ禡
                      
   }
	  /***************************************
	  *                                      *
	  *     �� Z �� 2 ���ʮɲM���׸ɵ{��     *
	  *                                      *
      ****************************************/
   public void shape01_fix(Graphics g) {
						 int py = (y-100)/25;                 // �p��C�y��
					     int px = (x-155)/25;                 // �p���y��	
					  if(y == 550 && py+2 < 21 && px+1 < 13) {                       // �O�_��F�U�ɭ� ?
						     							 
							 //shape01_1(g,x,y);                        // �I�s�e�� Z �� 2 �֤ߨ禡
							 
							 checked[py-1][px+1] = 5;           // �O���� , �b �� Z �� 2 �y�Ц�m��N���X "5"
							 checked[py][px] = 5;
							 checked[py][px+1] = 5;
							 checked[py+1][px] = 5;
							 checked[py+1][px+1] = 5;
						     checked[py+2][px] = 5; 
                             y = 100;                         // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                            index2 = (int)(Math.random() * 10);// �H������ϧ�
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                         // ����j�����|����
							return;
                      }   
                         
						 
					 
						 if(py > -1 && px > -1 && py+4 < 21 && px+1 < 13 && (checked[py+4][px] != -1  // �ˬd�w���m�U�����L����ϧ�
						                                            || checked[py+3][px+1] != -1)) {
						 
						    shape01_fix_1(g,x,y);             // �I�s �� Z �� 2 �M���׸ɮ֤ߵ{��
						 
						    return;
						 }	
						 shape01_fix_1(g,x,y);                // �I�s �� Z �� 2 �M���׸ɮ֤ߵ{��
   }
	  /***************************************
	  *                                      *
	  *          �g�� ø�ϳW�h�{��           *
	  *                                      *
      ****************************************/
   public void shape10(Graphics g) {
   
					  int py = (y-100)/25;                       // �p��C�y��
					  int px = (x-155)/25;                       // �p���y��	
					  
                      if(x >= 405) {                             // �g�� �k����ɭ���
		                  x = 405;
		              }
                      if(y >= 600) {                             // �g�� �U����ɭ���
		                  y = 575;
		              }
					  
					  if(py > -1 &&  px > -1 && py+2 < 21 && px+2 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1  // ���եثe�w���m�U��
					                                                                           || checked[py+2][px+2] != -1)) {  // ���L��L�ϧ�
						 
                         shape10_1(g,x,y);                       // �I�s�e�g�ή֤ߨ禡
						 
						 checked[py][px+1] = 1;                  // �U�観��L�ϧΫh�� �g�� �N���X "1"
					     checked[py+1][px] = 1;
						 checked[py+1][px+1] = 1;
						 checked[py+1][px+2] = 1;
							 
						 y = 75;                                 // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
                        
                         Music2();                               // ����j�����|����						 
						 return;
					  }
	  
					  shape10_1(g,x,y);                          // �I�s�e�g�ή֤ߨ禡
                      
   }
	  /***************************************
	  *                                      *
	  *       �g�� ���ʮɲM���׸ɵ{��        *
	  *                                      *
      ****************************************/
   public void shape10_fix(Graphics g) {
						 int py = (y-100)/25;                    // �p��C�y��
					     int px = (x-155)/25;                    // �p���y��
					  if(y >= 600 && py-1 > -1 && px+2 < 13) {                          // �O�_��F�U�ɭ� ?
						    							 
							 //shape10_1(g,x,y);                          // �I�s�e�g�ή֤ߨ禡
							 
						     checked[py-1][px+1] = 1;              // �O���� , �b �g�� �y�Ц�m��N���X "1"
							 checked[py][px] = 1;
							 checked[py][px+1] = 1;
							 checked[py][px+2] = 1;
							 
                             y = 100;                            // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                             index2 = (int)(Math.random() * 10); // �H������ϧ�
						     index = (int)queue.poll();
						     queue.offer(index2);
							
							Music2();                            // ����j�����|����
							return;
                      }   
                         
						 
						 if(py > -1 &&  px > -1 && px+2 < 13 && py+2 < 21 && (checked[py+2][px] != -1  // �ˬd�w���m�U�����L����ϧ�
						                || checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1)) {
						 
                            shape10_fix_1(g,x,y);                // �I�s �g�� �M���׸ɮ֤ߵ{��
					 
						    return;
						 }	
                         
                            shape10_fix_1(g,x,y);                // �I�s �g�� �M���׸ɮ֤ߵ{��
                         
   } 
	  /***************************************
	  *                                      *
	  *        �g�� 2 ø�ϳW�h�{��           *
	  *                                      *
      ****************************************/
   public void shape11(Graphics g) {
   
					  int py = (y-100)/25;                      // �p��C�y��
					  int px = (x-155)/25;                      // �p���y��	
  					  
                      if(x >= 430) {                            // �g�� 2 �k����ɭ���
		                  x = 430;
		              }
                      if(y >= 575) {                            // �g�� 2 �U����ɭ���
		                  y = 550;
		              }	
                       
					  
					  if(py > -1 &&  px > -1 && px+1 < 13 && py+3 < 21 && (checked[py+3][px] != -1 || checked[py+2][px+1] != -1)) {  // ���եثe�w���m�U��
						                                                                                                             // ���L��L�ϧ�
                         shape11_1(g,x,y);                     // �I�s�e�g�� 2 �֤ߨ禡					  
	 
    					 checked[py][px] = 6;                  // �U�観��L�ϧΫh�� �g�� 2 �N���X "6"
                         checked[py+1][px] = 6;
                         checked[py+2][px] = 6;
                         checked[py+1][px+1] = 6;	
							 
					     y = 75;                               // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
                         	
                         Music2();                             // ����j�����|����						 
						 return;
					  }							
	  
                      shape11_1(g,x,y);                        // �I�s�e�g�� 2 �֤ߨ禡		

   }
	  /***************************************
	  *                                      *
	  *     �g�� 2 ���ʮɲM���׸ɵ{��        *
	  *                                      *
      ****************************************/
   public void shape11_fix(Graphics g) {
						 int py = (y-100)/25;                  // �p��C�y��
					     int px = (x-155)/25;                  // �p���y��
                      if(y == 575 && py+1 < 21 && px+1 < 13) {                        // �O�_��F�U�ɭ� ?
						     							 
							 //shape11_1(g,x,y);                        // �I�s�e�g�� 2 �֤ߨ禡	
							 
						     checked[py-1][px] = 6;              // �O���� , �b �g�� 2 �y�Ц�m��N���X "6"
                             checked[py][px] = 6;
                             checked[py+1][px] = 6;
                             checked[py][px+1] = 6;							 
                             y = 100;                          // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                            index2 = (int)(Math.random() * 10);// �H������ϧ�
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // ����j�����|����
							return;
                      }				   
                          
						 

						 
						 if(py > -1 && px > -1 && py+3 < 21 && px+1 < 13 && (checked[py+3][px] != -1  // �ˬd�w���m�U�����L����ϧ�
						                                            || checked[py+2][px+1] != -1)) {
	                     
						    shape11_fix_1(g,x,y);              // �I�s �g�� 2 �M���׸ɮ֤ߵ{��
						 
						    return;
						 }
						 
						 shape11_fix_1(g,x,y);                 // �I�s �g�� 2 �M���׸ɮ֤ߵ{��
						 
   }
	  /***************************************
	  *                                      *
	  *        �g�� 3 ø�ϳW�h�{��           *
	  *                                      *
      ****************************************/
   public void shape12(Graphics g) {
   
				      int py = (y-100)/25;                     // �p��C�y��
				      int px = (x-155)/25;                     // �p���y��	

                      if(x >= 405) {                           // �g�� 3 �k����ɭ���
		                  x = 405;
		              }
                      if(y >= 600) {                           // �g�� 3 �U����ɭ���
		                  y = 575;
		              }
                      
				      if(py > -1 &&  px > -1 &&  py+2 < 21 && px+2 < 13 && (checked[py+1][px] != -1 || checked[py+2][px+1] != -1   // ���եثe�w���m�U��
					                                                                            || checked[py+1][px+2] != -1)) {   // ���L��L�ϧ�
						 
                          shape12_1(g,x,y);                    // �I�s�e�g�� 3 �֤ߨ禡
							 
					      checked[py][px] = 7;                 // �U�観��L�ϧΫh�� �g�� 3 �N���X "7"
                          checked[py][px+1] = 7;
                          checked[py][px+2] = 7;
                          checked[py+1][px+1] = 7;
                         	
                          Music2();                            // ����j�����|����						 
				          y = 75;                              // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
							 
					      return;
				      }
                     
                      shape12_1(g,x,y);                        // �I�s�e�g�� 3 �֤ߨ禡
					  
   }
	  /***************************************
	  *                                      *
	  *     �g�� 3 ���ʮɲM���׸ɵ{��        *
	  *                                      *
      ****************************************/
   public void shape12_fix(Graphics g) {
						 int py = (y-100)/25;                  // �p��C�y��
					     int px = (x-155)/25;                  // �p���y��
                      if(y == 600 && py-1 > -1 && px+2 < 13) {                        // �O�_��F�U�ɭ� ?
						     							 
							 //shape12_1(g,x,y);                    // �I�s�e�g�� 3 �֤ߨ禡
							 
						     checked[py-1][px] = 7;              // �O���� , �b �g�� 3 �y�Ц�m��N���X "7"
                             checked[py-1][px+1] = 7;
                             checked[py-1][px+2] = 7;
                             checked[py][px+1] = 7;							 
                             y = 100;                          // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                            index2 = (int)(Math.random() * 10);// �H������ϧ�
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // ����j�����|����
							return;
                      }  
					 
						 
						 if(py > -1 &&  px > -1 &&  py+2 < 21 && px+2 < 13 && (checked[py+1][px] != -1  // �ˬd�w���m�U�����L����ϧ�
						                 || checked[py+2][px+1] != -1 || checked[py+1][px+2] != -1)) {
						 
						    shape12_fix_1(g,x,y);              // �I�s �g�� 3 �M���׸ɮ֤ߵ{��

						    return;
						 }
						 
						 shape12_fix_1(g,x,y);                 // �I�s �g�� 3 �M���׸ɮ֤ߵ{��
							 
   }
	  /***************************************
	  *                                      *
	  *        �g�� 4 ø�ϳW�h�{��           *
	  *                                      *
      ****************************************/
   public void shape13(Graphics g) {
   
					  int py = (y-100)/25;                    // �p��C�y��
					  int px = (x-155)/25;                    // �p���y��
					 
                      if(x >= 430) {                          // �g�� 4 �k����ɭ���
		                  x = 430;
		              }	
                      if(y >= 575) {                          // �g�� 4 �U����ɭ���
		                  y = 550;
		              }					  
                      
					  if(py > -1 &&  px > -1 &&  px+1 < 13 &&  py+3 < 21 && (checked[py+2][px] != -1 || checked[py+3][px+1] != -1)) {   // ���եثe�w���m�U��
				                                                                                                                        // ���L��L�ϧ�
                         shape13_1(g,x,y);                    // �I�s�e�g�� 4 �֤ߨ禡		
				 
						 checked[py+1][px] = 8;               // �U�観��L�ϧΫh�� �g�� 4 �N���X "8"
                         checked[py][px+1] = 8;
                         checked[py+1][px+1] = 8;
                         checked[py+2][px+1] = 8;
						 
						 y = 75;                              // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
                         
                         Music2();                            // ����j�����|����						 
						 return;
						 
					  }
			  
                      shape13_1(g,x,y);                       // �I�s�e�g�� 4 �֤ߨ禡
							  
   }

	  /***************************************
	  *                                      *
	  *     �g�� 4 ���ʮɲM���׸ɵ{��        *
	  *                                      *
      ****************************************/
   public void shape13_fix(Graphics g) {
						 int py = (y-100)/25;                  // �p��C�y��
					     int px = (x-155)/25;                  // �p���y��
                      if(y == 575) {                        // �O�_��F�U�ɭ� ?
						     							 
							 //shape13_1(g,x,y);                    // �I�s�e�g�� 4 �֤ߨ禡
							 
						     checked[py][px] = 8;            // �O���� , �b �g�� 4 �y�Ц�m��N���X "8"
                             checked[py-1][px+1] = 8;
                             checked[py][px+1] = 8;
                             checked[py+1][px+1] = 8;							 
                             y = 100;                          // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                            index2 = (int)(Math.random() * 10);// �H������ϧ�
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // ����j�����|����
							return;
                      }   
                         
						 

					  
						 if(py > -1 &&  px > -1 && px+1 < 13 && py+3 < 21 && (checked[py+2][px] != -1  // �ˬd�w���m�U�����L����ϧ�
						                                             || checked[py+3][px+1] != -1)) {
						 
						    shape13_fix_1(g,x,y);              // �I�s �g�� 4 �M���׸ɮ֤ߵ{��

						    return;
						 }
						 
						 shape13_fix_1(g,x,y);                 // �I�s �g�� 4 �M���׸ɮ֤ߵ{��
   }
	  /***************************************
	  *                                      *
	  *        �p����� ø�ϳW�h�{��         *
	  *                                      *
      ****************************************/
   public void shape20(Graphics g) {

						 int py = (y-100)/25;                 // �p��C�y��
					     int px = (x-155)/25;                 // �p���y��
						 
						 if(y == 600) {
							 return;
						 }
						 if(py > -1 &&  px > -1 &&  py+1 < 21 && checked[py+1][px] != -1) {     // �p�G�U�@�欰 1 (�w����) , �e�X�ϧΫ�, ���X
						 	 											 
							shape20_1(g,x,y);                 // �I�s�e�p����ή֤ߨ禡 
							
							checked[py][px] = 2;              // �U�観��L�ϧΫh�� �p����� �N���X "2"
							
							y = 75;                           // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
                            
                            Music2();                         // ����j�����|����							
						    return;
						 }
						 
                    
                         shape20_1(g,x,y);                    // �I�s�e�p����ή֤ߨ禡
					 
   }
	  /***************************************
	  *                                      *
	  *      �p����� ���ʮɲM���׸ɵ{��     *
	  *                                      *
      ****************************************/
   public void shape20_fix(Graphics g) {
						 int py = (y-100)/25;                   // �p��C�y��
					     int px = (x-155)/25;                   // �p���y��
						 if(y == 625 && py-1 > -1) {                         // �O�_��F�U�ɭ� ?
						     							 
							 //shape20_1(g,x,y);                 // �I�s�e�p����ή֤ߨ禡
							 
						     checked[py-1][px] = 2;               // �O���� , �b �p����� �y�Ц�m��N���X "2"
							 
							 shape20_1(g,x,y-25);

                             y = 100;                           // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                            index2 = (int)(Math.random() * 10); // �H������ϧ�
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                           // ����j�����|����
							return;
                         }	                     
						                    
                       
						 if(py > -1 &&  px > -1 &&  py+1 < 21 && checked[py+1][px] != -1) { // �ˬd�w���m�U�����L����ϧ�
						    							
							shape20_fix_1(g,x,y);               // �I�s �p����� �M���׸ɮ֤ߵ{��
						 
							return;
						 }
						 
						 shape20_fix_1(g,x,y);                  // �I�s �p����� �M���׸ɮ֤ߵ{��

   }
	  /***************************************
	  *                                      *
	  *          ����� ø�ϳW�h�{��         *
	  *                                      *
      ****************************************/
   public void shape30(Graphics g) {
   
					  int py = (y-100)/25;                   // �p��C�y��
					  int px = (x-155)/25;                   // �p���y��

                      if(x >= 430) {                         // ����� �k����ɭ���
		                  x = 430;
		              }
                      if(y >= 600) {                         // ����� �U����ɭ���
		                  y = 575;
		              }
                      
					  if(py > -1 &&  px > -1 &&  py+2 < 21 && px+1 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1  )) {   // ���եثe�w���m�U��
						                                                                                                                 // ���L��L�ϧ�
                         shape30_1(g,x,y);                   // �I�s�e����ή֤ߨ禡
                         								 
						 checked[py][px] = 3;                // �U�観��L�ϧΫh�� ����� �N���X "3"
                         checked[py][px+1] = 3;	
					     checked[py+1][px] = 3;
						 checked[py+1][px+1] = 3;
							
						 y = 75;                             // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
                         
                         Music2();                           // ����j�����|����						 
						 return;
					  }
				  
                      shape30_1(g,x,y);                      // �I�s�e����ή֤ߨ禡
							  
   }
	  /***************************************
	  *                                      *
	  *      ����� ���ʮɲM���׸ɵ{��       *
	  *                                      *
      ****************************************/ 
   public void shape30_fix(Graphics g) {
						 int py = (y-100)/25;                  // �p��C�y��
					     int px = (x-155)/25;                  // �p���y��	   
	   
                      if(y == 600) {                        // �O�_��F�U�ɭ� ?
						     							 
							 //shape30_1(g,x,y);                   // �I�s�e����ή֤ߨ禡
							 
						     checked[py-1][px] = 3;              // �O���� , �b ����� �y�Ц�m��N���X "3"
                             checked[py-1][px+1] = 3;	
							 checked[py][px] = 3;
							 checked[py][px+1] = 3;
							 
                             y = 100;                          // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                            index2 = (int)(Math.random() * 10);// �H������ϧ�
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // ����j�����|����
							return;
                      }   
                         
						 
						 
						 if(py > -1 &&  px > -1 &&  py+2 < 21 && px+1 < 13 && (checked[py+2][px] != -1  // �ˬd�w���m�U�����L����ϧ�
						                                            || checked[py+2][px+1] != -1  )) {
						 
                            shape30_fix_1(g,x,y);              // �I�s ����� �M���׸ɮ֤ߵ{��
								
						    return;
							
						 }
						 
						 shape30_fix_1(g,x,y);                 // �I�s ����� �M���׸ɮ֤ߵ{��

   }
	  /***************************************
	  *                                      *
	  *          ����� ø�ϳW�h�{��         *
	  *                                      *
      ****************************************/
   public void shape40(Graphics g) {
   
					   int py = (y-100)/25;                 // �p��C�y��
					   int px = (x-155)/25;                 // �p���y��	

                       if(x >= 380) {                        // ����� �k����ɭ���
		                   x = 380;
		               }
                       						 					   
					   if(py > -1 &&  px > -1 &&  py+1 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+1] != -1   // ���եثe�w���m�U�� 
					                                               || checked[py+1][px+2] != -1 || checked[py+1][px+3] != -1 )) {   // ���L��L�ϧ�

                         shape40_1(g,x,y);                  // �I�s�e����ή֤ߨ禡
                         
						 checked[py][px] = 4;               // �U�観��L�ϧΫh�� ����� �N���X "4"
					     checked[py][px+1] = 4;
						 checked[py][px+2] = 4;
						 checked[py][px+3] = 4;
							
						 y = 75;                            // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
                         
                         Music2();                          // ����j�����|����						 
						 return;
					  }
			  
                      shape40_1(g,x,y);                     // �I�s�e����ή֤ߨ禡
						  
   }
	  /***************************************
	  *                                      *
	  *      ����� ���ʮɲM���׸ɵ{��       *
	  *                                      *
      ****************************************/ 
   public void shape40_fix(Graphics g) {
						 int py = (y-100)/25;                 // �p��C�y��
					     int px = (x-155)/25;                 // �p���y��	
                       if(y == 625 && px+3 < 13) {                       // �O�_��F�U�ɭ� ?
						     							 
							 //shape40_1(g,x,y);                  // �I�s�e����ή֤ߨ禡
							 
						     checked[py-1][px] = 4;             // �O���� , �b ����� �y�Ц�m��N���X "4"
							 checked[py-1][px+1] = 4;
							 checked[py-1][px+2] = 4;
							 checked[py-1][px+3] = 4;
                             y = 100;                         // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							 
                           index2 = (int)(Math.random() * 10);// �H������ϧ�
						   index = (int)queue.poll();
						   queue.offer(index2);
							
							Music2();                         // ����j�����|����
							return;
                       }   
                         
					 
						 if(py > -1 && px > -1 && py+1 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+1] != -1  // �ˬd�w���m�U�����L����ϧ�
						                                           || checked[py+1][px+2] != -1 || checked[py+1][px+3] != -1 )) {
						    
							shape40_fix_1(g,x,y);             // �I�s ����� �M���׸ɮ֤ߵ{��
							
						    return;
						 }
						 
						 shape40_fix_1(g,x,y);                // �I�s ����� �M���׸ɮ֤ߵ{��
						 
   }
	  /***************************************
	  *                                      *
	  *        ����� 2 ø�ϳW�h�{��         *
	  *                                      *
      ****************************************/
   public void shape41(Graphics g) {
   
					  int py = (y-100)/25;                  // �p��C�y��
					  int px = (x-155)/25;                  // �p���y��
					  
                      if(y >= 550) {                              // ����� 2 �U����ɭ���
		                  y = 525;
		              }					  
                      
					  if(py > -1 &&  px > -1 &&  py+4 < 21 && (checked[py+4][px] != -1)) {   // ���եثe�w���m�U��
   			                                                                                 // ���L��L�ϧ�		 
						   
                         shape41_1(g,x,y);                  // �I�s�e����� 2 �֤ߨ禡
                         	
						 checked[py][px] = 9;               // �U�観��L�ϧΫh�� ����� 2 �N���X "9"
                         checked[py+1][px] = 9;
                         checked[py+2][px] = 9;
                         checked[py+3][px] = 9;
                         
                         y = 75;                            // y �]�� 75 , �^��j��}�Y�A�[ 25 �h�� 100 , �A�~��ø��
						 Music2();                          // ����j�����|����	
						 return;
					  }
						 
                      shape41_1(g,x,y);                     // �I�s�e����� 2 �֤ߨ禡
						  
   }
	  /***************************************
	  *                                      *
	  *     ����� 2 ���ʮɲM���׸ɵ{��      *
	  *                                      *
      ****************************************/ 
   public void shape41_fix(Graphics g) {
	   
						 int py = (y-100)/25;                  // �p��C�y��
					     int px = (x-155)/25;                  // �p���y��	   

                      if(y >= 550 && py+2 < 21) {                        // �O�_��F�U�ɭ� ?

							//shape41_1(g,x,y);                  // �I�s�e����� 2 �֤ߨ禡
							
						    checked[py-1][px] = 9;               // �O���� , �b ����� 2 �y�Ц�m��N���X "9"
                            checked[py][px] = 9;
                            checked[py+1][px] = 9;
                            checked[py+2][px] = 9;							 
                            y = 100;                           // y �]�� 100 , �^��j��}�Y�A�[ 25 �h�� 125 , �A�~��M��ø��
							
                            index2 = (int)(Math.random() * 10);// �H������ϧ�
						    index = (int)queue.poll();
						    queue.offer(index2);
							
							Music2();                          // ����j�����|����
							return;
                      }   
					 
						 
						 if(py > -1 &&  px > -1 &&  py+4 < 21 && (checked[py+4][px] != -1)) { // �ˬd�w���m�U�����L����ϧ�
						  
						    shape41_fix_1(g,x,y);              // �I�s ����� 2 �M���׸ɮ֤ߵ{��
						  
						    return;
						 }
						 
						 shape41_fix_1(g,x,y);                 // �I�s ����� 2 �M���׸ɮ֤ߵ{��
						 
   }
   
  
	  /***************************************
	  *                                      *
	  *            ø�Ϯ֤ߵ{����            *
	  *                                      *
      ****************************************/   
   public void shape00_1(Graphics g, int x, int y) {              // �e�� Z �ή֤ߨ禡
	   
	                  g.setColor(Color.blue);                     // �� Z ��
                      g.fillRect(x,y,75,25);
                      g.setColor(Color.white);
                      for(int i = x; i < x + 75; i += 25)
                          g.drawRect(i,y,25,25);
                      g.setColor(Color.blue);                     // �� Z ��
                      g.fillRect(x+25,y+25,75,25);
                      g.setColor(Color.white);
                      for(int i = x+25; i < x + 100; i += 25)
                          g.drawRect(i,y+25,25,25);
					  
   }
   public void shape01_1(Graphics g, int x, int y) {              // �e�� Z �� 2 �֤ߨ禡
	                     
					  g.setColor(Color.blue);                     // �� Z �� 2
                         g.fillRect(x,y,50,100);
						 g.setColor(Color.white);
						 g.fillRect(x,y,25,25);
					     g.fillRect(x+25,y+75,25,25);       
						 g.setColor(Color.white);
                         for(int i = x; i < x + 50; i += 25)
                             for(int j = y; j < y + 100; j += 25)
                                 g.drawRect(i,j,25,25);
							 
   }
   public void shape10_1(Graphics g, int x, int y) {             // �e�g�ή֤ߨ禡
	   
	                  g.setColor(Color.yellow);                  // �g��
                      g.fillRect(x+25,y,25,25);
                      for(int i = x; i < x + 75; i += 25)
                          g.fillRect(i,y+25,25,25);
                      g.setColor(Color.white);
                      g.drawRect(x+25,y,25,25);
                      for(int i = x; i < x + 75; i += 25)
                          g.drawRect(i,y+25,25,25);
					  
   }
   public void shape11_1(Graphics g, int x, int y) {           // �e�g�� 2 �֤ߨ禡
	                 
					  g.setColor(Color.yellow);                // �g�� 2
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
   public void shape12_1(Graphics g, int x, int y) {           // �e�g�� 3 �֤ߨ禡
	   
	                  g.setColor(Color.yellow);                // �g�� 3
                      g.fillRect(x,y,75,25);
					  g.fillRect(x+25,y+25,25,25);
					  g.setColor(Color.white);
					  for(int i = x; i < x + 75; i += 25) 
					      for(int j = y; j < y + 50; j += 25)
                              g.drawRect(i,j,25,25);
	   
   }
   public void shape13_1(Graphics g, int x, int y) {          // �e�g�� 4 �֤ߨ禡
	   
	                     g.setColor(Color.yellow);            // �g�� 4
                      g.fillRect(x,y+25,25,25);
					  g.fillRect(x+25,y,25,75);
					  g.setColor(Color.white);
					  for(int i = x; i < x + 50; i += 25) 
					      for(int j = y; j < y + 75; j += 25)
                              g.drawRect(i,j,25,25);
	   
   }
   public void shape20_1(Graphics g, int x, int y) {          // �e�p����ή֤ߨ禡
	   
	                  g.setColor(Color.gray);                 // �p�����                    �e�X�ϧΫ�, �����X
                            g.fillRect(x,y,25,25);
                            g.setColor(Color.white);
                            g.drawRect(x,y,25,25);
	   
   }
   public void shape30_1(Graphics g, int x, int y) {         // �e����ή֤ߨ禡
	     
		               g.setColor(Color.red);                // �����
                      g.fillRect(x,y,50,50);
                      g.setColor(Color.white);
                      for(int i = x; i < x + 50; i += 25)
                          for(int j = y; j < y + 50; j += 25)
                              g.drawRect(i,j,25,25);
		 
   }
   public void shape40_1(Graphics g, int x, int y) {        // �e����ή֤ߨ禡
	   
	                  g.setColor(Color.orange);             // �����
                      g.fillRect(x,y,100,25);
                      g.setColor(Color.white);
                      for(int i = x; i < x + 100; i += 25)
                          g.drawRect(i,y,25,25);
	   
   }
   public void shape41_1(Graphics g, int x, int y) {        // �e����� 2 �֤ߨ禡
	                  
                         g.setColor(Color.orange);          // ����� 2
                      g.fillRect(x,y,25,100);
                      g.setColor(Color.white);
                      for(int j = y; j < y + 100; j += 25)
                          g.drawRect(x,j,25,25);
					  
   }


	  /***************************************
	  *                                      *
	  *          �M���׸ɮ֤ߵ{����          *
	  *                                      *
      ****************************************/

   public void shape00_fix_1(Graphics g, int x, int y) {          // �� Z �� �M���׸ɮ֤ߵ{��
                        if(y != 100) {
                               g.clearRect(x,y-25,75,25);         // �׸ɥ� Z �ΤU��
                               g.setColor(Color.white);
                               g.fillRect(x,y-25,75,25);
                               
                               g.clearRect(x+75,y,25,25);         // �׸ɥ� Z �ΤU��
                               g.setColor(Color.white);
                               g.fillRect(x+75,y,25,25);
                               
						    }
						    
						       g.clearRect(x-25,y,25,25);         // �׸ɥk��
						       g.setColor(Color.white);
						       g.fillRect(x-25,y,25,25);
                               
						       g.clearRect(x,y+25,25,25);         // �׸ɥk��
						       g.setColor(Color.white);
						       g.fillRect(x,y+25,25,25);
                               
							g.clearRect(x+75,y,25,25);            // �׸ɥ���
						    g.setColor(Color.white);
						    g.fillRect(x+75,y,25,25);
                            
						    g.clearRect(x+100,y+25,25,25);        // �׸ɥ���
						    g.setColor(Color.white);
						    g.fillRect(x+100,y+25,25,25);
                            							
							g.clearRect(x,y+25,25,25);            // �׸ɤW��
                            g.setColor(Color.white);
                            g.fillRect(x,y+25,25,25);
                            
                            g.clearRect(x+25,y+50,75,25);         // �׸ɤW��
                            g.setColor(Color.white);
                            g.fillRect(x+25,y+50,75,25);
                            
   }

   public void shape10_fix_1(Graphics g, int x, int y) {         // �g�� �M���׸ɮ֤ߵ{��
	                     if(y != 100) {
                         g.clearRect(x,y-25,75,75);              // �׸ɤg�ΤU��
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,75,75);
                         
						 }
						 
						 g.clearRect(x,y,25,25);                 // �׸ɥk��
						 g.setColor(Color.white);
						 g.fillRect(x,y,25,25);
						 
						 g.clearRect(x-25,y+25,25,25);           // �׸ɥk��
						 g.setColor(Color.white);
						 g.fillRect(x-25,y+25,25,25);
						 
                         g.clearRect(x+50,y,25,25);              // �׸ɥ���
						 g.setColor(Color.white);
						 g.fillRect(x+50,y,25,25);
						 
						 g.clearRect(x+75,y+25,25,25);           // �׸ɥ���
						 g.setColor(Color.white);
						 g.fillRect(x+75,y+25,25,25);
						 						 
						 g.clearRect(x,y+50,75,25);              // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,75,25);
                         
   }

   public void shape20_fix_1(Graphics g, int x, int y) {        // �p����� �M���׸ɮ֤ߵ{��
                         if(y != 100) {
						 g.clearRect(x,y-25,25,25);             // �׸ɤp����ΤU��
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,25,25);
                         
						 g.clearRect(x,y-50,25,25);             // �׸ɤp����ΤU��
                            g.setColor(Color.white);
                            g.fillRect(x,y-50,25,25);
                            
						 }
						 g.clearRect(x-25,y,25,25);            // �׸ɥk��
						 g.setColor(Color.white);
						 g.fillRect(x-25,y,25,25);
						 					
						 g.clearRect(x+25,y,25,25);            // �׸ɥ���
						 g.setColor(Color.white);
						 g.fillRect(x+25,y,25,25);
						 					
						 g.clearRect(x,y+25,25,25);            // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x,y+25,25,25);
                            
   }	   

   public void shape30_fix_1(Graphics g, int x, int y) {       // ����� �M���׸ɮ֤ߵ{��
	                     if(y != 100) {
                         g.clearRect(x,y-25,50,25);            // �׸ɥ���ΤU��
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,50,25);
                         
						 }
						 
                         g.clearRect(x-25,y,25,50);           // �׸ɥk��
                         g.setColor(Color.white);
                         g.fillRect(x-25,y,25,50);
                         
						 g.clearRect(x+50,y,25,50);           // �׸ɥ���
                         g.setColor(Color.white);
                         g.fillRect(x+50,y,25,50);
                         						  
						 g.clearRect(x,y+50,50,25);           // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,50,25);
                         						  
   }

   public void shape40_fix_1(Graphics g, int x, int y) {      // ����� �M���׸ɮ֤ߵ{��
	                     
						 if(y != 100) {
                         g.clearRect(x,y-25,100,25);          // �׸ɪ���ΤU��
                         g.setColor(Color.white);
                         g.fillRect(x,y-25,100,25);
                         
						 }
						 
						 g.clearRect(x-25,y,25,25);           // �׸ɥk��
						 g.setColor(Color.white);
						 g.fillRect(x-25,y,25,25);
						 
						 g.clearRect(x+100,y,25,25);          // �׸ɥ���
						 g.setColor(Color.white);
						 g.fillRect(x+100,y,25,25);
						 
						 g.clearRect(x,y+25,100,25);          // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x,y+25,100,25);
                         
							  
   }

   public void shape01_fix_1(Graphics g, int x, int y) {      // �� Z �� 2 �M���׸ɮ֤ߵ{��
	                     if(y != 100) {
                            g.clearRect(x+25,y-25,25,25);     // �׸ɥ� Z �� 2 �U��
                            g.setColor(Color.white);
                            g.fillRect(x+25,y-25,25,25);
                            
						 }
						 
                         g.clearRect(x-25,y+25,25,75);        // �׸ɥk��
                         g.setColor(Color.white);
                         g.fillRect(x-25,y+25,25,75);
                         
                         g.clearRect(x,y,25,25);              // �׸ɥk��
                         g.setColor(Color.white);
                         g.fillRect(x,y,25,25);
                         
						 
						 g.clearRect(x+25,y,25,25);           // �׸ɥ���
                         g.setColor(Color.white);
                         g.fillRect(x+25,y,25,25);
                         
                         g.clearRect(x+50,y,25,75);           // �׸ɥ���
                         g.setColor(Color.white);
                         g.fillRect(x+50,y,25,75);
                         						 
						 g.clearRect(x+25,y+75,25,25);        // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x+25,y+75,25,25);
                         
						 g.clearRect(x,y+100,25,25);          // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x,y+100,25,25);
                         
   }

   public void shape11_fix_1(Graphics g, int x, int y) {       // �g�� 2 �M���׸ɮ֤ߵ{��
	                     
						 if(y != 100) {
                            g.clearRect(x,y-25,25,25);         // �׸ɤg�� 2 �U��
                            g.setColor(Color.white);
                            g.fillRect(x,y-25,25,25);
                            
                            g.clearRect(x+25,y,25,25);         // �׸ɤg�� 2 �U��
                            g.setColor(Color.white);
                            g.fillRect(x+25,y,25,25);
                            
						 }
						 
                         g.clearRect(x-25,y,25,75);           // �׸ɥk��						 
						 g.setColor(Color.white);
						 g.fillRect(x-25,y,25,75);
                         						
                         g.clearRect(x+25,y,25,25);           // �׸ɥ���						 
						 g.setColor(Color.white);
						 g.fillRect(x+25,y,25,25);
                         
                         g.clearRect(x+50,y+25,25,25);        // �׸ɥ���						 
						 g.setColor(Color.white);
						 g.fillRect(x+50,y+25,25,25);
                         
						 g.clearRect(x+25,y+50,25,25);        // �׸ɥ���						 
						 g.setColor(Color.white);
						 g.fillRect(x+25,y+50,25,25);
                         						 
						 g.clearRect(x,y+75,25,25);           // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x,y+75,25,25);
                         
                         g.clearRect(x+25,y+50,25,25);        // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x+25,y+50,25,25);
                         
   }

   public void shape12_fix_1(Graphics g, int x, int y) {       // �g�� 3 �M���׸ɮ֤ߵ{��
	   
	                     if(y != 100) {
						    g.clearRect(x,y-25,75,75);         // �׸ɤg�� 3 �U��
                            g.setColor(Color.white);
                            g.fillRect(x,y-25,75,75);
                            
							}
							
                            g.clearRect(x-25,y,25,25);         // �׸ɥk��
                            g.setColor(Color.white);
                            g.fillRect(x-25,y,25,25);
                            
                            g.clearRect(x,y-25,25,25);         // �׸ɥk��
                            g.setColor(Color.white);
                            g.fillRect(x,y-25,25,25);
                            							
							g.clearRect(x+75,y,25,25);         // �׸ɥ���
                            g.setColor(Color.white);
                            g.fillRect(x+75,y,25,25);
                            
                            g.clearRect(x+50,y+25,25,25);      // �׸ɥ���
                            g.setColor(Color.white);
                            g.fillRect(x+50,y+25,25,25);
                            							
							g.clearRect(x,y+25,25,25);         // �׸ɤW��
                            g.setColor(Color.white);
                            g.fillRect(x,y+25,25,25);
                            
						    g.clearRect(x+25,y+50,25,25);      // �׸ɤW��
                            g.setColor(Color.white);
                            g.fillRect(x+25,y+50,25,25);
                            
						    g.clearRect(x+50,y+25,25,25);      // �׸ɤW��
                            g.setColor(Color.white);
                            g.fillRect(x+50,y+25,25,25);
                            	   
   }

   public void shape13_fix_1(Graphics g, int x, int y) {       // �g�� 4 �M���׸ɮ֤ߵ{��
	   	                 
						 if(y != 100) {
                            g.clearRect(x+25,y-25,25,25);      // �׸ɤg�� 4 �U��
                            g.setColor(Color.white);
                            g.fillRect(x+25,y-25,25,25);
                            
						    g.clearRect(x,y,25,25);            // �׸ɤg�� 4 �U��
                            g.setColor(Color.white);
                            g.fillRect(x,y,25,25);
                            
						 }
						 
						 g.clearRect(x-25,y+25,25,25);         // �׸ɥk��
                         g.setColor(Color.white);
                         g.fillRect(x-25,y+25,25,25);
                         
						 g.clearRect(x,y,25,25);               // �׸ɥk��
                         g.setColor(Color.white);
                         g.fillRect(x,y,25,25);
                         
						 g.clearRect(x,y+50,25,25);            // �׸ɥk��
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,25,25);
                         
						 g.clearRect(x+50,y,25,75);            // �׸ɥ���
                         g.setColor(Color.white);
                         g.fillRect(x+50,y,25,75);
                         
                         g.clearRect(x+25,y+75,25,25);         // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x+25,y+75,25,25);
                         
						 g.clearRect(x,y+50,25,25);            // �׸ɤW��
                         g.setColor(Color.white);
                         g.fillRect(x,y+50,25,25);
                         
   }

   public void shape41_fix_1(Graphics g, int x, int y) {       // ����� 2 �M���׸ɮ֤ߵ{��
	                     
						    if(y != 100) {
                               g.clearRect(x,y-25,25,25);      // �׸ɪ���� 2 �U��
                               g.setColor(Color.white);
                               g.fillRect(x,y-25,25,25);     
                               
							}
							
                            g.clearRect(x-25,y,25,100);        // �׸ɥk��
                            g.setColor(Color.white);
                            g.fillRect(x-25,y,25,100);
                            
						    g.clearRect(x+25,y,25,100);        // �׸ɥ���
                            g.setColor(Color.white);
                            g.fillRect(x+25,y,25,100);
                            
						    g.clearRect(x,y+100,25,25);        // �׸ɤW��
                            g.setColor(Color.white);
                            g.fillRect(x,y+100,25,25);
                            
   }
	  /***************************************
	  *                                      *
	  *             Windows �ƥ�             *
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

   public void keyPressed(KeyEvent e)                           // �M�w �e�i , ��h , ����, �k�� x,y ����m(���ʳW�h)
   {
	  /***************************************
	  *                                      *
	  *             �W��W���ƥ�             *
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
	  *             �U��U���ƥ�             *
	  *                                      *
      ****************************************/
      if(e.getKeyCode() == KeyEvent.VK_DOWN && y <= 625) {
		 int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 0) {      // �� Z ��
		        if(y >= 575) {         // �w�� �� Z �� �A�W�[�@������� , y > 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py+2 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1 || checked[py+2][px+3] != -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
         }
		 if(index == 1) {      // �g��
		        if(y >= 575) {         // �w�� �g�� �A�W�[�@������� , y > 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py+2 < 21 && px+2 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1 || checked[py+2][px+2] != -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
         }
		 if(index == 2) {      // �p�����
		        if(y >= 600) {         // �w�� �p����� �A�W�[�@������� , y > 600 ��, �b�}�C�~�|�ˬd���� !!!
                          return;
                }
		        if(py+1 < 21 && checked[py+1][px] != -1) {   // �p�G�U�䤣�� -1
						  return;
                }
				
		 }
		 if(index == 3) {      // �����
		        if(y >= 575) {         // �w�� ����� �A�W�[�@������� , y > 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py+2 < 21 && px+1 < 13 && (checked[py+2][px] != -1 || checked[py+2][px+1] != -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
         }
		 if(index == 4) {      // �����
		        if(y >= 600) {         // �w�� ����� �A�W�[�@������� , y > 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py+1 < 21 && px+3 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+1] != -1 || checked[py+1][px+2] != -1 || checked[py+1][px+3] != -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
		 }
		 if(index == 5) {      // �� Z �� 2
		        if(y >= 525) {         // �w�� �� Z �� 2 �A�W�[�@������� , y > 525 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py+4 < 21 && px+1 < 13 && (checked[py+4][px] != -1 || checked[py+3][px+1] != -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
		 }
		 if(index == 6) {        // �g�� 2
		        if(y >= 550) {         // �w�� �g�� 2 �A�W�[�@������� , y > 550 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py+3 < 21 && px+1 < 13 && (checked[py+3][px] != -1 || checked[py+2][px+1] != -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
		 }
		 if(index == 7) {        // �g�� 3
		        if(y >= 575) {         // �w�� �g�� 3 �A�W�[�@������� , y > 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py+2 < 21 && px+2 < 13 && (checked[py+1][px] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+1] != -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
		 }
		 if(index == 8) {        // �g�� 4
                if(y >= 550) {         // �w�� �g�� 4 �A�W�[�@������� , y > 550 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py+3 < 21 && px+1 < 13 && (checked[py+2][px] != -1 || checked[py+3][px+1]!= -1)) {   // �p�G�U�䤣�� -1
						  return;
                }
		 }
		 if(index == 9) {      // ����� 2
                 if(y >= 525) {         // �w�� ����� 2 �A�W�[�@������� , y > 525 ��, �b�}�C�~�|�ˬd���� !!!
						   return;
                 }
		          if(py+4 < 21 && (checked[py+4][px] != -1)) {
						    return;                       // �p�G�U�䤣�� -1
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
	  *             ���䥪���ƥ�             *
	  *                                      *
      ****************************************/
      else if(e.getKeyCode() == KeyEvent.VK_LEFT && x >= 155) {
		 int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 0) {      // �� Z ��
		        if(y >= 600) {         // �w�� �� Z �� �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && px > -1 && py+1 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px] != -1)) {   // �p�G���䤣�� -1
						  return;
                }
         }
		 if(index == 1) {      // �g��
		        if(y >= 600) {         // �w�� �g�� �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px-1 > -1 && (checked[py+1][px-1] != -1 || checked[py][px] != -1)) {   // �p�G���䤣�� -1
						  return;
                }
         }
		 if(index == 2) {      // �p�����
		        if(y >= 625) {         // �w�� �p����� �A�W�[�@������� , y == 625 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py < 21 && py > -1 && px-1 > -1 && checked[py][px-1] != -1) {   // �p�G���䤣�� -1
						  return;
                }
		 }
		 if(index == 3) {      // �����
		        if(y >= 600) {         // �w�� ����� �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px-1] != -1)) {   // �p�G���䤣�� -1
						  return;
                }
         }
		 if(index == 4) {      // �����
		        if(y >= 625) {         // �w�� ����� �A�W�[�@������� , y == 625 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && px-1 > -1 && checked[py][px-1] != -1) {   // �p�G���䤣�� -1
						  return;
                }
		 }
		 if(index == 5) {      // �� Z �� 2
		        if(y >= 550) {         // �w�� �� Z �� 2 �A�W�[�@������� , y == 550 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && py+3 < 21 && px-1 > -1 && (checked[py+1][px-1] != -1 || checked[py+2][px-1] != -1 || checked[py+3][px-1] != -1 || checked[py][px] != -1)) {   // �p�G���䤣�� -1
						  return;
                }
		 } 
		 if(index == 6) {        // �g�� 2
		        if(y >= 575) {         // �w�� �g�� 2 �A�W�[�@������� , y == 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px-1] != -1 || checked[py+2][px-1] != -1)) {   // �p�G���䤣�� -1
						  return;
                }
		 }
		 if(index == 7) {        // �g�� 3
		        if(y >= 600) {         // �w�� �g�� 3 �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && py+1 < 21 && px-1 > -1 && (checked[py][px-1] != -1 || checked[py+1][px] != -1)) {   // �p�G���䤣�� -1
						  return;
                }
		 }
		 if(index == 8) {        // �g�� 4
		        if(y >= 575) {         // �w�� �g�� 4 �A�W�[�@������� , y == 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px-1 > -1 && (checked[py][px] != -1 || checked[py+1][px-1] != -1 || checked[py+2][px] != -1 )) {   // �p�G���䤣�� -1
						  return;
                }
		 }
		 if(index == 9) {      // ����� 2
		          if(y >= 550) {         // �w�� ����� 2 �A�W�[�@������� , y == 550 ��, �b�}�C�~�|�ˬd���� !!!
						    return;
                  }
		          if(py > -1 && px-1 > -1 && py+3 < 21 && (checked[py][px-1] != -1 || checked[py+1][px-1] != -1 || checked[py+2][px-1] != -1 || checked[py+3][px-1] != -1)) {
						    return;                       // �p�G���䤣�� -1
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
	  *             �k��k���ƥ�             *
	  *                                      *
      ****************************************/
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 0 || index == 4) && x <= 380) {     // �� z �� , ����� �V�k�W�h
	     int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 0) {      // �� Z ��
		        if(y >= 600) {         // �w�� �� Z �� �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px+4 < 13 && (checked[py][px+3] != -1 || checked[py+1][px+4] != -1)) {   // �p�G�k�䤣�� -1
						  return;
                }
         }
	     if(index == 4) {      // �����
		        if(y >= 625) {         // �w�� ����� �A�W�[�@������� , y == 625 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && py < 21 && px+4 < 13 && checked[py][px+4] != -1) {   // �p�G�k�䤣�� -1
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
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 1 || index == 7) && x <= 405) {     // �g�� , �g�� 3 �V�k�W�h
	     int py = (y-100)/25;
		 int px = (x-155)/25;
	     if(index == 1) {      // �g��
		        if(y >= 600) {         // �w�� �g�� �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py+1 < 21 && px+3 < 13 && py > -1 && (checked[py+1][px+3] != -1 || checked[py][px+2] != -1)) {   // �p�G�k�䤣�� -1
						  return;
                }
         }
		 if(index == 7) {        // �g�� 3
		        if(y >= 600) {         // �w�� �g�� 3 �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && px+3 < 13 && py+1 < 21 && (checked[py][px+3] != -1 || checked[py+1][px+2] != -1)) {   // �p�G�k�䤣�� -1
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
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 3 || index == 5 || index == 6 || index == 8) && x <= 430) {     // �� z �� 2 , ����� , �g�� 2 , �g�� 4 �V�k�W�h
	     int py = (y-100)/25;
		 int px = (x-155)/25;
		 if(index == 3) {      // �����
		        if(y >= 600) {         // �w�� ����� �A�W�[�@������� , y == 600 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py > -1 && py+1 < 21 && px+2 < 13 && (checked[py][px+2] != -1 || checked[py+1][px+2] != -1)) {   // �p�G�k�䤣�� -1
						  return;
                }
         }
	     if(index == 5) {      // �� Z �� 2
		        if(y >= 550) {         // �w�� �� Z �� 2 �A�W�[�@������� , y == 550 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
	            if(py > -1 && py+3 < 21 && px+2 < 13 && (checked[py][px+2] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+2] != -1 || checked[py+3][px+1] != -1)) {   // �p�G�k�䤣�� -1
						  return;
                }
         }
		 if(index == 6) {        // �g�� 2
		        if(y >= 575) {         // �w�� �g�� 2 �A�W�[�@������� , y == 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px+2 < 13 && (checked[py][px+1] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+1] != -1)) {   // �p�G�k�䤣�� -1
						  return;
                }
		 }
		 if(index == 8) {        // �g�� 4
		        if(y >= 575) {         // �w�� �g�� 4 �A�W�[�@������� , y == 575 ��, �b�}�C�~�|�ˬd���� !!!
						  return;
                }
		        if(py > -1 && py+2 < 21 && px+2 < 13 && (checked[py][px+2] != -1 || checked[py+1][px+2] != -1 || checked[py+2][px+2] != -1 )) {   // �p�G�k�䤣�� -1
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
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (index == 2 || index == 9) && x <= 455) {      // �p����� , ����� 2 �V�k�W�h
	        int py = (y-100)/25;
		    int px = (x-155)/25;
	        if(index == 2) {      // �p�����
		          if(y >= 625) {         // �w�� �p����� �A�W�[�@������� , y == 625 ��, �b�}�C�~�|�ˬd���� !!!
						    return;
                  }
		          if(py > -1 && py < 21 && px+1 < 13 && checked[py][px+1] != -1) {   // �p�G�k�䤣�� -1
						    return;
                  }
		    }
            if(index == 9) {      // ����� 2
		          if(y >= 550) {         // �w�� ����� 2 �A�W�[�@������� , y == 550 ��, �b�}�C�~�|�ˬd���� !!!
						    return;
                  }
		          if(py > -1 && px+1 < 13 && py+3 < 21 && (checked[py][px+1] != -1 || checked[py+1][px+1] != -1 || checked[py+2][px+1] != -1 || checked[py+3][px+1] != -1)) {
						    return;                           // �p�G�k�䤣�� -1
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
	  *            �ť����ഫ�ƥ�            *
	  *                                      *
      ****************************************/
      else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 0) {                   // ���ť����ഫ�ϧ� ( �� Z �� �� �� Z �� 2 )
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
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 5) {                   // ���ť����ഫ�ϧ� ( �� Z �� 2 �� �� Z �� )
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
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 1) {                   // ���ť����ഫ�ϧ� ( �g�� �� �g�� 2 )
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
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 6) {                   // ���ť����ഫ�ϧ� ( �g�� 2 �� �g�� 3 )
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
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 7) {                   // ���ť����ഫ�ϧ� ( �g�� 3 �� �g�� 4 )
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
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 8) {                   // ���ť����ഫ�ϧ� ( �g�� 4 �� �g�� )
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
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 9) {                   // ���ť����ഫ�ϧ� ( ����� 2 �� ����� )
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
	  else if(e.getKeyCode() == KeyEvent.VK_SPACE && index == 4) {                   // ���ť����ഫ�ϧ� ( ����� �� ����� 2 )
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


