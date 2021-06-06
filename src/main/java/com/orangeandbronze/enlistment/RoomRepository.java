package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.DATA.*;
import java.util.*;

public class RoomRepository {

	public Collection<Room> findAll() {
		return ROOMS;
	}
}
