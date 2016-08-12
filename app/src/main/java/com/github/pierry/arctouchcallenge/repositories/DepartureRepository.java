package com.github.pierry.arctouchcallenge.repositories;

import com.activeandroid.query.Select;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureRepository;
import java.util.List;
import org.androidannotations.annotations.EBean;

@EBean public class DepartureRepository implements IDepartureRepository {

  @Override public List<Departure> get() {
    return new Select().from(Departure.class).execute();
  }

  @Override public List<Departure> getWeekdayByRouteId(long id) {
    return new Select().from(Departure.class)
        .where("RouteId=" + id)
        .and("Calendar='WEEKDAY'")
        .execute();
  }

  @Override public List<Departure> getSaturdayByRouteId(long id) {
    return new Select().from(Departure.class)
        .where("RouteId=" + id)
        .and("Calendar='SATURDAY'")
        .execute();
  }

  @Override public List<Departure> getSundayByRouteId(long id) {
    return new Select().from(Departure.class)
        .where("RouteId=" + id)
        .and("Calendar='SUNDAY'")
        .execute();
  }

  @Override public long create(Departure departure) {
    return departure.save();
  }
}
