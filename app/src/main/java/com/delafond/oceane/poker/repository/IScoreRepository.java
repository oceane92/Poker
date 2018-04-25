package com.delafond.oceane.poker.repository;

import com.delafond.oceane.poker.model.Scores;
import java.util.ArrayList;

public interface IScoreRepository {

    public boolean add(Scores m);

    public boolean remove(Scores m);

    public boolean isScores(Scores m);

    public ArrayList<Scores> getAll();

}
