package com.kferenc.jpf.dao;

import com.kferenc.jpf.model.Activity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("activityDao")
public class ActivityDaoImpl extends AbstractDao<Activity, Long> implements ActivityDao {

    @Override
    public Activity getActivity(Long id) {
        return getByKey(id);
    }

    @Override
    public Activity getActivityBySlug(String slug) {
        return getBy("slug", slug);
    }

    @Override
    public Activity getActivityByName(String name) {
        return getBy("name", name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Activity> listActivities() {
        return createCriteria().list();
    }

    @Override
    public Long addActivity(Activity activity) {
        return save(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
        update(activity);
    }

    @Override
    public void removeActivity(Long id) {
        delete(getByKey(id));
    }

}
