package com.github.pierry.arctouchcallenge.domain.contracts;

import com.github.pierry.arctouchcallenge.domain.Stop;
import java.util.List;

public interface IStopRepository {

  List<Stop> get();

  Stop getById(long id);

  long create(Stop stop);

  List<Stop> getByRouteId(long routId);
}
