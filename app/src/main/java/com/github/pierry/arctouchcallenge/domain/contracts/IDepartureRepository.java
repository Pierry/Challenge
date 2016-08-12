package com.github.pierry.arctouchcallenge.domain.contracts;

import com.github.pierry.arctouchcallenge.domain.Departure;
import java.util.List;

public interface IDepartureRepository {

  List<Departure> get();

  List<Departure> getWeekdayByRouteId(long id);

  List<Departure> getSaturdayByRouteId(long id);

  List<Departure> getSundayByRouteId(long id);

  long create(Departure departure);
}
