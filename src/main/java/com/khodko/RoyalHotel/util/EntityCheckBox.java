package com.khodko.RoyalHotel.util;

import java.util.ArrayList;
import java.util.List;


public class EntityCheckBox<T> {

    private static final String CHECKED = "checked";

    private final T entity;
    private String checked = "";

    public EntityCheckBox(T entity) {
        this.entity = entity;
    }

    public EntityCheckBox(T entity, boolean checked) {
        this.entity = entity;
        if (checked) {
            checked();
        } else {
            notChecked();
        }
    }

    public T getEntity() {
        return entity;
    }

    public String getChecked() {
        return checked;
    }

    public void checked() {
        checked = CHECKED;
    }

    public void notChecked() {
        checked = "";
    }

    public static <T> List<EntityCheckBox<T>> getEntityCheckBoxes(List<T> fullList, List<T> checkedList) {
        List<EntityCheckBox<T>> entityCheckBoxes = new ArrayList<>();
        for (T object : fullList) {
            boolean checked = false;
            for (T checkedObject : checkedList) {
                if (object.equals(checkedObject)) {
                    checked = true;
                    break;
                }
            }
            entityCheckBoxes.add(new EntityCheckBox<>(object, checked));
        }
        return entityCheckBoxes;
    }

    public static <T> List<EntityCheckBox<T>> getEntityCheckBoxes(List<T> fullList, T checkedEntity) {
        List<EntityCheckBox<T>> entityCheckBoxes = new ArrayList<>();
        for (T object : fullList) {
            boolean checked = object.equals(checkedEntity);
            entityCheckBoxes.add(new EntityCheckBox<>(object, checked));
        }
        return entityCheckBoxes;
    }

    public static <T> List<EntityCheckBox<T>> getEntityCheckBoxes(List<T> fullList) {
        List<EntityCheckBox<T>> entityCheckBoxes = new ArrayList<>();
        for (T object : fullList) {
            entityCheckBoxes.add(new EntityCheckBox<>(object));
        }
        return entityCheckBoxes;
    }
}
