package hu.schonherz.training.landing.web.managedbeans.request;

import hu.schonherz.training.band.ejb.remote.stateless.BandRemoteService;
import hu.schonherz.training.band.vo.BandVo;
import hu.schonherz.training.landing.web.util.RemoteEJBClientLookup;
import hu.schonherz.training.venue.ejb.remote.stateless.VenueRemoteService;
import hu.schonherz.training.venue.vo.VenueVo;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@ManagedBean(name = "bandsAndVenues")
@RequestScoped
public class BandsAndVenuesMB {

    private VenueRemoteService venueRemoteService;
    private BandRemoteService bandRemoteService;

    private List<BandVo> bands;
    private List<VenueVo> venues;

    private BandVo selectedBand;
    private VenueVo selectedVenue;

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

    public void showVenueProfile() {
        if (selectedVenue != null) {
            String url = "/../venue/profile.xhtml?venueid=" + selectedVenue.getId();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ResourceBundle bundle;
            try {
                bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            } catch (MissingResourceException e) {
                bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    bundle.getString("lists.noneSelected.summary"), bundle.getString("lists.noneSelected.detail")));
        }
    }

    public void showBandProfile() {
        if (selectedBand != null) {
            String url = "/../band/profile.xhtml?id=" + selectedBand.getId();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ResourceBundle bundle;
            try {
                bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            } catch (MissingResourceException e) {
                bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    bundle.getString("lists.noneSelected.summary"), bundle.getString("lists.noneSelected.detail")));
        }
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

    public BandVo getSelectedBand() {
        return selectedBand;
    }

    public void setSelectedBand(BandVo selectedBand) {
        this.selectedBand = selectedBand;
    }

    public VenueVo getSelectedVenue() {
        return selectedVenue;
    }

    public void setSelectedVenue(VenueVo selectedVenue) {
        this.selectedVenue = selectedVenue;
    }
}
