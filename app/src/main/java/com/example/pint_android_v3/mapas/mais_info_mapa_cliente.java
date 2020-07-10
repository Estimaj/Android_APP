package com.example.pint_android_v3.mapas;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.security.AuthenticationManager;
import com.esri.arcgisruntime.security.DefaultAuthenticationChallengeHandler;
import com.esri.arcgisruntime.security.OAuthConfiguration;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import com.esri.arcgisruntime.tasks.networkanalysis.Route;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteParameters;

import com.esri.arcgisruntime.tasks.networkanalysis.RouteResult;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteTask;
import com.esri.arcgisruntime.tasks.networkanalysis.Stop;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.quem_vai_consigo.quem_vai_consigo;
import com.example.pint_android_v3.quem_vai_consigo.quem_vai_consigo_condutor;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class mais_info_mapa_cliente extends barra_lateral_pro {

    private MapView mMapView = null;
    private GraphicsOverlay mGraphicsOverlay;
    private Point mStart;
    private Point mEnd;
    private int user_id;
    private int idViagem;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mais_info_mapa_cliente);

        // Retrieve the map and initial extent from XML layout
        //1
        mMapView = findViewById(R.id.mapView_mais_info_cliente);
        mStart = new Point(40.6573504,-7.9142947);
        mEnd = new Point(40.6573504,-7.91429);
        setupMap();


        //2
        createGraphicsOverlay();

        setupOAuthManager();
        setStartMarker(mStart);
        setEndMarker(mEnd);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            try {
                Button btnPassageiros = findViewById(R.id.ver_passageiros_mais_info_cliente);
                btnPassageiros.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Click_Quem_Vai();
                    }
                });

                colocarValoresMaisInfo(b);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        Bar_Settings(user_id);
    }

    private void colocarValoresMaisInfo(Bundle b) {

        String localPartida = (String) b.get("localPartida");
        String localChegada = (String) b.get("localChegada");


        TextView valorViagemtxt = findViewById(R.id.Dinheiro_pagar_mais_info_cliente);
        valorViagemtxt.setText("" + (String) b.get("valorViagem"));

        idViagem = (int) b.get("idViagem");

        TextView localPartidatxtview = findViewById(R.id.Local_Partida_mais_info_cliente);
        localPartidatxtview.setText(localPartida);
        TextView localChegadatxtview = findViewById(R.id.Local_Chegada_mais_info_cliente);
        localChegadatxtview.setText(localChegada);

        ImageView certoGone;
        if((int) b.get("bagagemPedido") == 0){
            certoGone = findViewById(R.id.mala_icon_mais_info_cliente);
            certoGone.setVisibility(View.GONE);
        }
        if((int) b.get("animalPedido") == 0){
            certoGone = findViewById(R.id.canideo_icon_mais_info_cliente);
            certoGone.setVisibility(View.GONE);
        }
        if((int) b.get("necessidadesEspeciaisPedido") == 0){
            certoGone = findViewById(R.id.wheel_icon_mais_info_cliente);
            certoGone.setVisibility(View.GONE);
        }


        TextView localPartidaCoord = findViewById(R.id.viagens_efetuadas_adapter_Local_Partida_coordenadas);
        localPartidaCoord.setText("" + (String) b.get("localPartidaCoord"));
        TextView localChegadaCoord = findViewById(R.id.viagens_efetuadas_adapter_Local_Chegada_coordenadas);
        localChegadaCoord.setText(""+ (String) b.get("localChegadaCoord"));
    }

    public void Click_Quem_Vai(){
        Intent Consigo = new Intent(mais_info_mapa_cliente.this, quem_vai_consigo.class);
        Consigo.putExtra("user_id", user_id);
        Consigo.putExtra("idViagem", idViagem);
        startActivity(Consigo);
    }


    private void createGraphicsOverlay() {
        mGraphicsOverlay = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(mGraphicsOverlay);
    }

    private void setMapMarker(Point location, SimpleMarkerSymbol.Style style, int markerColor, int outlineColor) {
        float markerSize = 8.0f;
        float markerOutlineThickness = 2.0f;
        SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(style, markerColor, markerSize);
        pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, outlineColor, markerOutlineThickness));
        Graphic pointGraphic = new Graphic(location, pointSymbol);
        mGraphicsOverlay.getGraphics().add(pointGraphic);
    }

    private void setStartMarker(Point location) {
        //mGraphicsOverlay.getGraphics().clear();
        setMapMarker(location, SimpleMarkerSymbol.Style.DIAMOND, Color.rgb(226, 119, 40), Color.BLUE);
        mStart = location;
        //mEnd = null;
    }

    private void setEndMarker(Point location) {
        setMapMarker(location, SimpleMarkerSymbol.Style.SQUARE, Color.rgb(40, 119, 226), Color.RED);
        mEnd = location;
        findRoute();
    }

    /*private void mapClicked(Point location) {
        if (mStart == null) {
            // Start is not set, set it to a tapped location
            setStartMarker(location);
        } else if (mEnd == null) {
            // End is not set, set it to the tapped location then find the route
            setEndMarker(location);
        } else {
            // Both locations are set; re-set the start to the tapped location
            setStartMarker(location);
        }
        findRoute();
    }*/

    private void setupOAuthManager() {
        String clientId = getResources().getString(R.string.client_id);
        String redirectUrl = getResources().getString(R.string.redirect_url);

        try {
            OAuthConfiguration oAuthConfiguration = new OAuthConfiguration("https://www.arcgis.com", clientId, redirectUrl);
            DefaultAuthenticationChallengeHandler authenticationChallengeHandler = new DefaultAuthenticationChallengeHandler(this);
            AuthenticationManager.setAuthenticationChallengeHandler(authenticationChallengeHandler);
            AuthenticationManager.addOAuthConfiguration(oAuthConfiguration);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Log.d("FindRoute", message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void setupMap() {
        if (mMapView != null) {
            //Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
            double latitude = 40.6573504;//40.6573504,-7.9142947
            double longitude = -7.9142947;
            int levelOfDetail = 13;
            ArcGISMap map = new ArcGISMap(Basemap.Type.STREETS, latitude, longitude, levelOfDetail);
            mMapView.setMap(map);


            /*//marcar pontos com o dedo
            mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
                @Override public boolean onSingleTapConfirmed(MotionEvent e) {
                    android.graphics.Point screenPoint = new android.graphics.Point(
                            Math.round(e.getX()),
                            Math.round(e.getY()));
                    Point mapPoint = mMapView.screenToLocation(screenPoint);
                    mapClicked(mapPoint);
                    return super.onSingleTapConfirmed(e);
                }
            });*/

            //ArcGISMapImageLayer traffic = new ArcGISMapImageLayer(getResources().getString(R.string.traffic_service));
            //map.getOperationalLayers().add(traffic);
        }

    }

    private void findRoute() {
        String routeServiceURI = getResources().getString(R.string.routing_url);
        final RouteTask solveRouteTask = new RouteTask(getApplicationContext(), routeServiceURI);
        solveRouteTask.loadAsync();

        solveRouteTask.addDoneLoadingListener(() -> {
            if (solveRouteTask.getLoadStatus() == LoadStatus.LOADED) {
                final ListenableFuture<RouteParameters> routeParamsFuture = solveRouteTask.createDefaultParametersAsync();
                routeParamsFuture.addDoneListener(() -> {
                    try {
                        RouteParameters routeParameters = routeParamsFuture.get();
                        List<Stop> stops = new ArrayList<>();
                        stops.add(new Stop(mStart));
                        stops.add(new Stop(mEnd));
                        routeParameters.setStops(stops);
                        final ListenableFuture<RouteResult> routeResultFuture = solveRouteTask.solveRouteAsync(routeParameters);
                        routeResultFuture.addDoneListener(() -> {
                            try {
                                RouteResult routeResult = routeResultFuture.get();
                                Route firstRoute = routeResult.getRoutes().get(0);
                                Polyline routePolyline = firstRoute.getRouteGeometry();
                                SimpleLineSymbol routeSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 4.0f);
                                Graphic routeGraphic = new Graphic(routePolyline, routeSymbol);
                                mGraphicsOverlay.getGraphics().add(routeGraphic);
                            } catch (InterruptedException | ExecutionException e) {
                                showError("Solve RouteTask failed " + e.getMessage());
                            }
                        });
                    } catch (InterruptedException | ExecutionException e) {
                        showError("Cannot create RouteTask parameters " + e.getMessage());
                    }
                });
            } else {
                showError("Unable to load RouteTask " + solveRouteTask.getLoadStatus().toString());
            }
        });
    }

    /*@Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            mMapView.dispose();
        }
        super.onDestroy();
    }*/






}
