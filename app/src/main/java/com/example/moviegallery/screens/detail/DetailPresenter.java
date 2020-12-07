package com.example.moviegallery.screens.detail;

import com.example.moviegallery.pojo.Cast;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.pojo.Writer;

import java.util.ArrayList;
import java.util.List;

public class DetailPresenter {

    public DetailView detailView;

    public DetailPresenter(DetailView detailView) {
        this.detailView = detailView;
    }

    public List<Cast> makeListOfCast(Movie movie) {
        String[] castInArr = movie.getActors().split(", ");
        List<Cast> cast = new ArrayList<>();
        for (String name: castInArr) {
            Cast c = new Cast(name);
            cast.add(c);
        }
        return cast;
    }

    public List<Writer> makeListOfWriters(Movie movie) {
        String allWriters = movie.getWriter();
        String currentPos = "";

        List<Writer> writers = new ArrayList<>();

        //List of writers with their positions
        String[] writersWithPos = allWriters.split(", ");

        for (String writer : writersWithPos) {
            //Separate author with role
            String[] writerSep = writer.split(" \\(");

            if (writerSep.length == 1) {
                writers.add(new Writer(writerSep[0], ""));
            } else {
                String role = writerSep[1].substring(0,1).toUpperCase() + writerSep[1].replace(")", "").substring(1);
                if (role.equals(currentPos)) {
                    writers.add(new Writer(writerSep[0], ""));
                } else {
                    writers.add(new Writer(writerSep[0], role));
                    currentPos = role;
                }
            }
        }

        return writers;
    }
}
