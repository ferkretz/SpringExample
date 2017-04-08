package com.kferenc.jpf.service;

import com.kferenc.jpf.model.Activity;
import java.util.List;

public interface ActivityService {

    public Activity getActivity(Long id);

    public Activity getActivityBySlug(String slug);

    public Activity getActivityByName(String name);

    public List<Activity> listActivities();

    public Long addActivity(Activity activity);

    public void updateActivity(Activity activity);

    public void removeActivity(Long id);

}
