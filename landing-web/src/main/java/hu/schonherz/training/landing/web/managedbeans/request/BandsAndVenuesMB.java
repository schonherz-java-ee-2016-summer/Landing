package hu.schonherz.training.landing.web.managedbeans.request;

import hu.schonherz.training.band.ejb.remote.stateless.BandRemoteService;
import hu.schonherz.training.band.vo.BandVo;
import hu.schonherz.training.landing.web.util.RemoteEJBClientLookup;
import hu.schonherz.training.venue.ejb.remote.stateless.VenueRemoteService;
import hu.schonherz.training.venue.vo.VenueVo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;
import java.util.List;

@ManagedBean(name = "bandsAndVenues")
@RequestScoped
public class BandsAndVenuesMB {

    private VenueRemoteService venueRemoteService;
    private BandRemoteService bandRemoteService;

    private List<BandVo> bands;
    private List<VenueVo> venues;

    @PostConstruct
    public void init() {
        final String bandAppName = "Band-application";
        final String bandModuleName = "band-service-1.0-SNAPSHOT";
        final String bandDistinctName = "";
        final String bandBeanName = "BandService";

        final String venueAppName = "Venue";
        final String venueModuleName = "venue-service";
        final String venueDistinctName = "";
        final String venueBeanName = "VenueService";

        try {
            venueRemoteService = RemoteEJBClientLookup.lookup(VenueRemoteService.class, venueAppName,
                    venueModuleName, venueDistinctName, venueBeanName);
            bandRemoteService = RemoteEJBClientLookup.lookup(BandRemoteService.class, bandAppName,
                    bandModuleName, bandDistinctName, bandBeanName);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        bands = bandRemoteService.getAllBands();
        venues = venueRemoteService.getAllVenues();
    }

    public List<BandVo> getBands() {
        return bands;
    }

    public void setBands(List<BandVo> bands) {
        this.bands = bands;
    }

    public List<VenueVo> getVenues() {
        return venues;
    }

    public void setVenues(List<VenueVo> venues) {
        this.venues = venues;
    }
}
