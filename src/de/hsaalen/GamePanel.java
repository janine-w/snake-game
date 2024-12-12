package de.hsaalen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    public final int width_in_tiles = 30;
    public final int height_in_tiles = 30;
    public final int tile_size_in_pixels = 10;
    public final int maximum_snake_length = 900;
    public final int game_loop_duration_in_ms = 140;
    public final int initial_snake_size = 3;

    public Snake snake;

    private int current_snake_size;
    private int apple_x;
    private int apple_y;

    private Direction direction = Direction.right;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public GamePanel() {
        
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(width_in_pixels(), height_in_pixels()));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    private void initGame() {

        place_snake_at_initial_location();
        
        place_apple_at_random_location();

        start_game_loop_timer();
    }

    public void start_game_loop_timer(){
        timer = new Timer(game_loop_duration_in_ms, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) 
		{
			IntPair apple_position = new IntPair( apple_x, apple_y);
			IntPair apple_position_in_pixels = pixel_position_of_tile( apple_position );

            g.drawImage(apple, apple_position_in_pixels.x, apple_position_in_pixels.y, this);
            for (int i = 0; i < snake.length(); i++) 
			{
				IntPair position_in_pixels = pixel_position_of_tile( snake.position(i) );
                if (i == 0) 
				{
                    g.drawImage(head, position_in_pixels.x, position_in_pixels.y, this);
                } else 
				{
                    g.drawImage(ball, position_in_pixels.x, position_in_pixels.y, this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (width_in_pixels() - metr.stringWidth(msg)) / 2, height_in_pixels() / 2);
    }

    private void checkApple() 
	{
        if ((snake.head_position().x == apple_x) && (snake.head_position().y == apple_y)) 
		{
			snake.grow( direction );
            place_apple_at_random_location();
        }
    }

    private void move() 
	{
		snake.move( direction );
    }

    private void checkCollision()
	{
		if ( snake.is_snake_colliding( width_in_tiles, height_in_tiles) )
			inGame = false;

        if (!inGame) {
            timer.stop();
        }
    }

    public int maximum_tile_index_x(){
        return width_in_tiles - 1;
    }

    public int maximum_tile_index_y(){
        return height_in_tiles -1;
    }

    public int width_in_pixels()
	{
		return width_in_tiles * tile_size_in_pixels;
	}

	public int height_in_pixels()
	{
		return height_in_tiles * tile_size_in_pixels;
	}

	public IntPair pixel_position_of_tile( IntPair position )
	{
		return new IntPair( position.x * tile_size_in_pixels, position.y * tile_size_in_pixels );
	}

    public void place_snake_at_initial_location(){
        snake = new Snake(3);
    }

    private void place_apple_at_random_location() {

        apple_x = (int) (Math.random() * maximum_tile_index_x());
        apple_y = (int) (Math.random() * maximum_tile_index_y());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ( key == KeyEvent.VK_LEFT )
                direction = Direction.left;

            if ( key == KeyEvent.VK_RIGHT )
                direction = Direction.right;

            if ( key == KeyEvent.VK_UP )
                direction = Direction.up;

            if ( key == KeyEvent.VK_DOWN )
                direction = Direction.down;
        }
    }
}
