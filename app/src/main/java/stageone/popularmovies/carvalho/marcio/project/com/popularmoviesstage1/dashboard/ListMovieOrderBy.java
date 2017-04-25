package stageone.popularmovies.carvalho.marcio.project.com.popularmoviesstage1.dashboard;

public enum ListMovieOrderBy {
    POPULARITY (1),
    RATING (2);

    private int order;

    ListMovieOrderBy(int order) {
        this.order = order;
    }

    public int getOrder(){
        return this.order;
    }

    public static ListMovieOrderBy getOrderby(int svdOb) {
        ListMovieOrderBy orderBy = null;
        for (ListMovieOrderBy orders : ListMovieOrderBy.values()) {
            if (orders.order == svdOb) {
                orderBy = orders;
                break;
            }
        }
        return orderBy;

    }
}
