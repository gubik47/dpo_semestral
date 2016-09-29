package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.entity.GameObject;

/**
 * Created by Jakub on 05.11.2015.
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
