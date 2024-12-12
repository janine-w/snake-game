package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void test_maximum_tile_index_x(){
        Board board = new Board();
        int maximum_tile_index_x = board.maximum_tile_index_x();
        assertEquals( (maximum_tile_index_x + 1 ) * board.tile_size_in_pixels, board.width_in_pixels );
    } 

    @Test
    public void testConcatenate() {
        Board board = new Board();
        assertNotNull( board );
    }
}