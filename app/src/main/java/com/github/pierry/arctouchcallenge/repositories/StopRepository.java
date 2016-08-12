package com.github.pierry.arctouchcallenge.repositories;

import android.util.Log;
import com.activeandroid.query.Select;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.github.pierry.arctouchcallenge.domain.contracts.IStopRepository;
import java.util.List;
import org.androidannotations.annotations.EBean;

@EBean public class StopRepository implements IStopRepository {

  @Override public List<Stop> get() {
    return new Select().from(Stop.class).execute();
  }

  @Override public List<Stop> getByRouteId(long id) {
    String log =
        new Select().from(Stop.class).where("RouteId =" + id).orderBy("Sequence ASC").toSql();
    Log.e("Log", log);
    return new Select().from(Stop.class).where("RouteId =" + id).orderBy("Sequence ASC").execute();
  }

  @Override public Stop getById(long id) {
    return new Select().from(Stop.class).where("StopId=" + id).executeSingle();
  }

  @Override public long create(Stop stop) {
    return stop.save();
  }
}
