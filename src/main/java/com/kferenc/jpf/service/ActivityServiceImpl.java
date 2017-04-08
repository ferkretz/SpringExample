package com.kferenc.jpf.service;

import com.kferenc.jpf.dao.ActivityDao;
import com.kferenc.jpf.model.Activity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("activityService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public Activity getActivity(Long id) {
        return activityDao.getActivity(id);
    }

    @Override
    public Activity getActivityBySlug(String slug) {
        return activityDao.getActivityBySlug(slug);
    }

    @Override
    public Activity getActivityByName(String name) {
        return activityDao.getActivityByName(name);
    }

    @Override
    public List<Activity> listActivities() {
        return activityDao.listActivities();
    }

    @Override
    public Long addActivity(Activity activity) {
        return activityDao.addActivity(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
        activityDao.updateActivity(activity);
    }

    @Override
    public void removeActivity(Long id) {
        activityDao.removeActivity(id);
    }

}
