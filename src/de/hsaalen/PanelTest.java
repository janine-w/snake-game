package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

public class PanelTest 
{
	@Test
	public void test_maximum_tile_index_x()
	{
		GamePanel panel = new GamePanel();
		int maximum_tile_index_x = panel.maximum_tile_index_x();
		assertEquals( maximum_tile_index_x + 1, panel.width_in_tiles );
	}

	@Test
	public void  test_maximum_tile_index_y()
	{
		GamePanel panel = new GamePanel();
		int maximum_tile_index_y = panel.maximum_tile_index_y();
		assertEquals( maximum_tile_index_y + 1, panel.width_in_tiles );
	}

    @Test
    public void test_constructor() {
        GamePanel panel = new GamePanel();
        assertNotNull( panel );
    }
}