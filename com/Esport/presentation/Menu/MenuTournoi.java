public class MenuTournoi {

    private final TournoiService tournoiService;

    public MenuTournoi(TournoiService tournoiService) {
        this.tournoiService = tournoiService;
    }

    public void afficherMenuTournoi(){
        LoggerUtil.info("Menu Tournoi");
    }   


}
