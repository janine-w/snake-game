package de.hsaalen;

import java.util.List;
import java.util.LinkedList;

public class Game 
{
	public final int width_in_tiles  = 30;
    public final int height_in_tiles = 30;
	public final int initial_snake_size = 3;
    public final int amount_obstacles = 10;
    public final IntPair initial_snake_position = new IntPair(5, 5); 
    
    public List<IntPair> obstacles;
    public Snake snake;
    public IntPair apple_position; 
	public Direction direction = Direction.right;

    public boolean inGame = true;
    public boolean super_apple = false;

	public Game( ) 
	{
        allocate_memory();
        place_obstacles_at_random_location(amount_obstacles);
		place_snake_at_initial_location();        
        place_apple_at_random_location();	
	}
    
    private void allocate_memory()
    {
        obstacles = new LinkedList<IntPair>();
    }

	public void place_snake_at_initial_location() 
	{
		snake = new Snake( initial_snake_size, initial_snake_position );
	}

    private void place_obstacles_at_random_location(int amount_obstacles){
        
        for(int i=0; i < amount_obstacles; i++){
            int obstacle_x = (int) (Math.random() * width_in_tiles - 1); 
            int obstacle_y = (int) (Math.random() * height_in_tiles - 1);
            IntPair obstacle_position = new IntPair(obstacle_x, obstacle_y);

            obstacles.add(obstacle_position);
            System.out.println(obstacle_position(i));
        }
    }

    public IntPair obstacle_position( int index )
	{
		return obstacles.get( index );
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
		if ( snake.is_snake_colliding( width_in_tiles, height_in_tiles , obstacles ) ){
			inGame = false;
        }
    }
}