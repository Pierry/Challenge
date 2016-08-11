package com.github.pierry.arctouchcallenge.domain.contracts;

import com.github.pierry.arctouchcallenge.domain.Departure;
import java.util.List;

public interface IDepartureRepository {

  List<Departure> get();

  Departure getById(long id);

  long create(Departure departure);
}
