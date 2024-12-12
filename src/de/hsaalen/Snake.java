package de.hsaalen;

import java.util.List;
import java.util.LinkedList;

public class Snake {


    List<IntPair> snake_body_tiles;

    public Snake( int initial_snake_size, IntPair intial_snake_position) 
    {
        allocate_memory();
        place_at_initial_location( initial_snake_size, intial_snake_position );
    }

    private void allocate_memory()
	{
		snake_body_tiles = new LinkedList<IntPair>();
	}

	public void place_at_initial_location( int initial_snake_size, IntPair initial_snake_position) 
	{
        for (int i = 0; i < initial_snake_size; i++) 
		{   
            IntPair new_snake_body_tile = new IntPair(initial_snake_position.x - i, initial_snake_position.y);
			snake_body_tiles.add(new_snake_body_tile);
        }		
	}

	public void move( Direction direction )
	{
		for ( int i = length()-1; i > 0; i-- )
		{
			position( i ).x = position( i-1 ).x;
			position( i ).y = position( i-1 ).y;
        }
		head_position().move( direction );
 	}

	public void grow( Direction direction )
	{
		IntPair new_head_position = head_position().clone();
		new_head_position.move( direction );
		snake_body_tiles.add( 0, new_head_position );
 	}

	public boolean is_snake_colliding( int board_width_in_tiles, int board_height_in_tiles )
	{
		if ( is_colliding_with_itself() )
			return true;	
        if ( is_outside_board( board_width_in_tiles, board_height_in_tiles ) )
            return true;			

		return false;
	}

	public boolean is_colliding_with_itself()
	{
        for ( int i = length()-1; i > 1; i-- )
		{
			if ( position( i ).x == head_position().x &&
                 position( i ).y == head_position().y )
				return true;
		}
		return false;
 	}

	public boolean is_outside_board( int board_width_in_tiles, int board_height_in_tiles )
	{
		if ( head_position().x < 0 )
			return true;
		if ( head_position().x >= board_width_in_tiles )
			return true;
		if ( head_position().y < 0 )
			return true;
		if ( head_position().y >= board_height_in_tiles )
			return true;
		return false;
	}

	public int length() 
	{
		return snake_body_tiles.size();
	}

	public IntPair position( int index )
	{
		return snake_body_tiles.get( index );
	}

	public IntPair head_position()
	{
		return position( 0 );
	}

	public String toString() 
	{
		String result = "Snake" + snake_body_tiles.toString();
		return result;
	}
}