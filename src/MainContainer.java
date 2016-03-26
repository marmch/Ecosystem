import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainContainer extends JFrame implements ChangeListener{
	JSlider wolfSlider;
	JSlider elkSlider;
	JSlider vegSlider;
	JSlider treeSlider;
	JSlider waterSlider;
	JSlider bankSlider;
	JSlider damSlider;
	JSlider animSlider;
	JSlider beavSlider;
	JSlider fSlider;
	int woVal = 0;
	int eVal = 80;
	int vVal = 12;
	int tVal = 7;
	int waVal = 9;
	int baVal = 7;
	int dVal = 5;
	int aVal = 8;
	int beVal = 5;
	int fVal = 0;
	
	Thread thread = new Thread(){
		public void run(){
			while(true){
				fVal = fSlider.getValue();
				//System.out.println("Thread Running");
				int elkBal = (80 - wolfSlider.getValue()*2/3);
				elkBal -= fVal*elkBal/100;
				if(elkSlider.getValue() > elkBal){
					int dif = getDifference(elkSlider.getValue(), elkBal, 0.5);
					elkSlider.setValue(elkSlider.getValue() - dif);
				}
				else if(elkSlider.getValue() < elkBal){
					int dif = getDifference(elkBal, elkSlider.getValue(), 0.1);
					elkSlider.setValue(elkSlider.getValue() + dif);
				}
				
				if(wolfSlider.getValue() > elkSlider.getValue()*2){
					int dif = getDifference(wolfSlider.getValue(), elkSlider.getValue()*2, 0.5);
					wolfSlider.setValue(wolfSlider.getValue() - dif);
				}
				
				int vegBal = ((int) (98-0.00186*Math.pow(elkSlider.getValue(), 2.7) + 
						0.0000371*Math.pow(elkSlider.getValue(), 3.5)));
				vegBal -= fVal*vegBal/100;
				if(vegSlider.getValue() > vegBal){
					int dif = getDifference(vegSlider.getValue(), vegBal, 0.5);
					vegSlider.setValue(vegSlider.getValue() - dif);
				}
				else if(vegSlider.getValue() < vegBal){
					int dif = getDifference(vegBal, vegSlider.getValue(), 0.5);
					vegSlider.setValue(vegSlider.getValue() + dif);
				}
				
				int bankBal = (vegSlider.getValue() - 5);
				bankBal -= fVal*bankBal/100;
				if(bankSlider.getValue() > bankBal){
					int dif = getDifference(bankSlider.getValue(), bankBal, 0.5);
					bankSlider.setValue(bankSlider.getValue() - dif);
				}
				else if(bankSlider.getValue() < bankBal){
					int dif = getDifference(bankBal, bankSlider.getValue(), 0.5);
					bankSlider.setValue(bankSlider.getValue() + dif);
				}
				
				int treeBal = (bankSlider.getValue() + 2);
				treeBal -= fVal*treeBal/100;
				if(treeSlider.getValue() > treeBal){
					int dif = getDifference(treeSlider.getValue(), treeBal, 0.5);
					treeSlider.setValue(treeSlider.getValue() - dif);
				}
				else if(treeSlider.getValue() < treeBal){
					int dif = getDifference(treeBal, treeSlider.getValue(), 0.5);
					treeSlider.setValue(treeSlider.getValue() + dif);
				}
				
				int waterBal = (treeSlider.getValue() + 2);
				waterBal -= fVal*waterBal/100;
				if(waterSlider.getValue() > waterBal){
					int dif = getDifference(waterSlider.getValue(), waterBal, 0.5);
					waterSlider.setValue(waterSlider.getValue() - dif);
				}
				else if(waterSlider.getValue() < waterBal){
					int dif = getDifference(waterBal, waterSlider.getValue(), 0.5);
					waterSlider.setValue(waterSlider.getValue() + dif);
				}
				
				int animBal = ((int) (0.9*Math.pow(waterSlider.getValue(),1.03)));
				animBal -= fVal*animBal/100;
				if(animSlider.getValue() > animBal){
					int dif = getDifference(animSlider.getValue(), animBal, 0.5);
					animSlider.setValue(animSlider.getValue() - dif);
				}
				else if(animSlider.getValue() < animBal){
					int dif = getDifference(animBal, animSlider.getValue(), 0.5);
					animSlider.setValue(animSlider.getValue() + dif);
				}
				
				int beavBal = ((int) (0.7*waterSlider.getValue()));
				beavBal -= fVal*beavBal/100;
				if(beavSlider.getValue() > beavBal){
					int dif = getDifference(beavSlider.getValue(), beavBal, 0.5);
					beavSlider.setValue(beavSlider.getValue() - dif);
				}
				else if(beavSlider.getValue() < beavBal){
					int dif = getDifference(beavBal, beavSlider.getValue(), 0.5);
					beavSlider.setValue(beavSlider.getValue() + dif);
				}
				
				int damBal = ((int) (1.1*beavSlider.getValue()));
				damBal -= fVal*damBal/100;
				if(damSlider.getValue() > damBal){
					int dif = getDifference(damSlider.getValue(), damBal, 0.5);
					damSlider.setValue(damSlider.getValue() - dif);
				}
				else if(damSlider.getValue() < damBal){
					int dif = getDifference(damBal, damSlider.getValue(), 0.5);
					damSlider.setValue(damSlider.getValue() + dif);
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	public MainContainer() throws IOException{
		super("Dynamic Systems");
		
		setUpContainer();
		
		BufferedImage titleImageR = ImageIO.read(getClass().getResource("/gfx/title_block.png"));
		int scaledWidth = (int) (titleImageR.getWidth()*0.5);
		int scaledHeight = (int) (titleImageR.getHeight()*0.5);
		BufferedImage titleImage = getScaledImage(titleImageR, scaledWidth, scaledHeight);
		JLabel title = new JLabel(new ImageIcon(titleImage));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(title);
		
		BufferedImage subtitleImageR = ImageIO.read(getClass().getResource("/gfx/sub_titles.png"));
		int sscaledWidth = (int) (subtitleImageR.getWidth()*0.5);
		int sscaledHeight = (int) (subtitleImageR.getHeight()*0.5);
		BufferedImage subtitleImage = getScaledImage(subtitleImageR, sscaledWidth, sscaledHeight);
		JLabel subtitle = new JLabel(new ImageIcon(subtitleImage));
		subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(subtitle);
		
		BufferedImage row1R = ImageIO.read(getClass().getResource("/gfx/row1.png"));
		int r1scaledWidth = (int) (row1R.getWidth()*0.5);
		int r1caledHeight = (int) (row1R.getHeight()*0.5);
		BufferedImage row1 = getScaledImage(row1R, r1scaledWidth, r1caledHeight);
		JLabel row1L = new JLabel(new ImageIcon(row1));
		row1L.setAlignmentX(Component.CENTER_ALIGNMENT);
		row1L.setMaximumSize(new Dimension(100,200));
		this.add(row1L);
		
		BufferedImage row2R = ImageIO.read(getClass().getResource("/gfx/row2.png"));
		int r2scaledWidth = (int) (row2R.getWidth()*0.5);
		int r2caledHeight = (int) (row2R.getHeight()*0.5);
		BufferedImage row2 = getScaledImage(row2R, r2scaledWidth, r2caledHeight);
		JLabel row2L = new JLabel(new ImageIcon(row2));
		row2L.setAlignmentX(Component.CENTER_ALIGNMENT);
		row2L.setMaximumSize(new Dimension(100,200));
		this.add(row2L);
		
		BufferedImage row3R = ImageIO.read(getClass().getResource("/gfx/row3.png"));
		int r3scaledWidth = (int) (row3R.getWidth()*0.5);
		int r3caledHeight = (int) (row3R.getHeight()*0.5);
		BufferedImage row3 = getScaledImage(row3R, r3scaledWidth, r3caledHeight);
		JLabel row3L = new JLabel(new ImageIcon(row3));
		row3L.setAlignmentX(Component.CENTER_ALIGNMENT);
		row3L.setMaximumSize(new Dimension(100,300));
		this.add(row3L);
		
		BufferedImage disasterImageR = ImageIO.read(getClass().getResource("/gfx/disaster.png"));
		int dscaledWidth = (int) (disasterImageR.getWidth()*0.43);
		int dscaledHeight = (int) (disasterImageR.getHeight()*0.43);
		BufferedImage disasterImage = getScaledImage(disasterImageR, dscaledWidth, dscaledHeight);
		JLabel disaster = new JLabel(new ImageIcon(disasterImage));
		disaster.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(disaster);
		
		BufferedImage fireImageR = ImageIO.read(getClass().getResource("/gfx/fire.png"));
		int fscaledWidth = (int) (fireImageR.getWidth()*0.7);
		int fscaledHeight = (int) (fireImageR.getHeight()*0.7);
		BufferedImage fireImage = getScaledImage(fireImageR, fscaledWidth, fscaledHeight);
		JLabel fire = new JLabel(new ImageIcon(fireImage));
		fire.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(fire);
		
		wolfSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, woVal);
		setUpSlider(wolfSlider, 1);
		this.add(wolfSlider);
		
		elkSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, eVal);
		setUpSlider(elkSlider, 1);
		this.add(elkSlider);
		
		vegSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, vVal);
		setUpSlider(vegSlider, 2);
		this.add(vegSlider);
		
		treeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, tVal);
		setUpSlider(treeSlider, 2);
		this.add(treeSlider);
		
		waterSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, waVal);
		setUpSlider(waterSlider, 2);
		this.add(waterSlider);
		
		bankSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, baVal);
		setUpSlider(bankSlider, 2);
		this.add(bankSlider);
		
		damSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, dVal);
		setUpSlider(damSlider, 2);
		this.add(damSlider);
		
		animSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, aVal);
		setUpSlider(animSlider, 3);
		this.add(animSlider);
		
		beavSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, beVal);
		setUpSlider(beavSlider, 3);
		this.add(beavSlider);
		
		fSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, fVal);
		setUpSlider(fSlider, 4);
		this.add(fSlider);
		
		thread.start();
		
		
		Insets insets = this.getInsets();
		Dimension size = title.getPreferredSize();
		title.setBounds(insets.left+20, insets.top+20, size.width, size.height);
		
		size = subtitle.getPreferredSize();
		subtitle.setBounds(insets.left+20, insets.top+220, size.width, size.height);
		
		size = row1L.getPreferredSize();
		row1L.setBounds(insets.left+20, insets.top+290, size.width, size.height);
		
		size = row2L.getPreferredSize();
		row2L.setBounds(insets.left+365, insets.top+291, size.width, size.height);
		
		size = row3L.getPreferredSize();
		row3L.setBounds(insets.left+730, insets.top+291, size.width, size.height);
		
		size = disaster.getPreferredSize();
		disaster.setBounds(insets.left+730, insets.top+460, size.width, size.height);
		
		size = fire.getPreferredSize();
		fire.setBounds(insets.left+730, insets.top+527, size.width, size.height);

		size = wolfSlider.getPreferredSize();
		wolfSlider.setBounds(100 + insets.left, 286 + insets.top, size.width, size.height);
		
		size = elkSlider.getPreferredSize();
		elkSlider.setBounds(100 + insets.left, 351 + insets.top, size.width, size.height);
		
		size = vegSlider.getPreferredSize();
		vegSlider.setBounds(460 + insets.left, 286 + insets.top, size.width, size.height);
		
		size = treeSlider.getPreferredSize();
		treeSlider.setBounds(460 + insets.left, 351 + insets.top, size.width, size.height);
		
		size = waterSlider.getPreferredSize();
		waterSlider.setBounds(460 + insets.left, 413 + insets.top, size.width, size.height);
		
		size = bankSlider.getPreferredSize();
		bankSlider.setBounds(460 + insets.left, 476 + insets.top, size.width, size.height);
		
		size = damSlider.getPreferredSize();
		damSlider.setBounds(460 + insets.left, 539 + insets.top, size.width, size.height);
		
		size = animSlider.getPreferredSize();
		animSlider.setBounds(820 + insets.left, 286 + insets.top, size.width, size.height);
		
		size = beavSlider.getPreferredSize();
		beavSlider.setBounds(820 + insets.left, 351 + insets.top, size.width, size.height);
		
		size = fSlider.getPreferredSize();
		fSlider.setBounds(insets.left+820, insets.top+520, size.width, size.height);
		
		
	}
	
	private void setUpContainer(){
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1050, 650);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		
	}
	
	private BufferedImage getScaledImage(BufferedImage src, int w, int h){
	    int finalw = w;
	    int finalh = h;
	    double factor = 1.0d;
	    if(src.getWidth() > src.getHeight()){
	        factor = ((double)src.getHeight()/(double)src.getWidth());
	        finalh = (int)(finalw * factor);                
	    }else{
	        factor = ((double)src.getWidth()/(double)src.getHeight());
	        finalw = (int)(finalh * factor);
	    }   

	    BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(src, 0, 0, finalw, finalh, null);
	    g2.dispose();
	    return resizedImg;
	}
	
	private void setUpSlider(JSlider slider, int ui){
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(0);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setPreferredSize(new Dimension(200,50));
		if(ui == 1)
			slider.setUI(new SliderUI1(slider));
		else if(ui == 2){
			slider.setUI(new SliderUI2(slider));
			Hashtable labels = new Hashtable();
	        labels.put(0, new JLabel("0%"));
	        labels.put(20, new JLabel("20%"));
	        labels.put(40, new JLabel("40%"));
	        labels.put(60, new JLabel("60%"));
	        labels.put(80, new JLabel("80%"));
	        labels.put(100, new JLabel("100%"));
	        slider.setLabelTable(labels);
		}
		else if(ui == 3)
			slider.setUI(new SliderUI3(slider));
		else if(ui == 4){
			slider.setUI(new SliderUI4(slider));
			Hashtable labels = new Hashtable();
	        labels.put(0, new JLabel("0%"));
	        labels.put(20, new JLabel("20%"));
	        labels.put(40, new JLabel("40%"));
	        labels.put(60, new JLabel("60%"));
	        labels.put(80, new JLabel("80%"));
	        labels.put(100, new JLabel("100%"));
	        slider.setLabelTable(labels);
		}
		slider.setBackground(Color.WHITE);
		slider.setFocusable(false);
	}
	
	private int getDifference(int a, int b, double c){
		return (int) Math.ceil(Math.pow(a - b, c) / (1/c));
	}
}
