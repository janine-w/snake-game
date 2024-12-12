package de.hsaalen;


public class Game 
{
	public final int width_in_tiles  = 30;
    public final int height_in_tiles = 30;
	public final int initial_snake_size = 3;
    public final IntPair initial_snake_position = new IntPair(5, 5); 

    public Snake snake;
    public IntPair apple_position; 
	public Direction direction = Direction.right;

    public boolean inGame = true;
    public boolean super_apple = false;

	public Game( ) 
	{
		place_snake_at_initial_location();        
        place_apple_at_random_location();	
	}

	public void place_snake_at_initial_location() 
	{
		snake = new Snake( initial_snake_size, initial_snake_position );
	}

	private void place_apple_at_random_location() 
	{
        super_apple = place_super_apple();
        int apple_x = (int) (Math.random() * width_in_tiles - 1);
        int apple_y = (int) (Math.random() * height_in_tiles - 1);
		apple_position = new IntPair( apple_x, apple_y );
    }

    private boolean place_super_apple(){
        if(Math.random() > 0.6){
            return true;
        }
        return false;
    }

	public void handle_round()
	{
		if ( !inGame ) 
			return;

		checkApple();
        checkCollision();
        move();
	}

	private void checkApple() 
	{
        if ((snake.head_position().x == apple_position.x) && (snake.head_position().y == apple_position.y)) {
            snake.grow( direction, super_apple);
            place_apple_at_random_location();
        }
    }

    private void move() 
	{
		snake.move( direction );
    }

    private void checkCollision()
	{
		if ( snake.is_snake_colliding( width_in_tiles, height_in_tiles ) ){
			inGame = false;
        }
    }
}