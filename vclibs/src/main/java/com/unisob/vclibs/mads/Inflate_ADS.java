package com.unisob.vclibs.mads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.nativead.NativeAdView;
//import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.unisob.vclibs.R;

import java.util.ArrayList;
import java.util.List;

public class Inflate_ADS {
    Context activity;

    public Inflate_ADS(Context context) {
        this.activity = context;
    }

    /*public void inflate_NB_STARTAPP(final ArrayList<NativeAdDetails> ads, ViewGroup linearLayout) {
        try {

            LayoutInflater from = LayoutInflater.from(activity);
            int i = 0;
            View adView = from.inflate(R.layout.ads_nb_startapp, null);

            linearLayout.removeAllViews();
            linearLayout.addView(adView);

            ImageView native_ad_icon;
            TextView native_ad_title, native_ad_body;
            Button native_ad_call_to_action;

            native_ad_icon = adView.findViewById(R.id.native_ad_icon);
            native_ad_title = adView.findViewById(R.id.native_ad_title);
            native_ad_body = adView.findViewById(R.id.native_ad_body);
            native_ad_call_to_action = adView.findViewById(R.id.native_ad_call_to_action);

            native_ad_icon.setImageBitmap(ads.get(0).getSecondaryImageBitmap());

            native_ad_title.setText(ads.get(0).getTitle());
            native_ad_body.setText(ads.get(0).getDescription());


            ads.get(0).registerViewForInteraction( native_ad_icon);
            ads.get(0).registerViewForInteraction( native_ad_title);
            ads.get(0).registerViewForInteraction( native_ad_body);
            ads.get(0).registerViewForInteraction( native_ad_call_to_action);


        } catch (Exception e) {

        }
    }*/

    public void inflate_NB_FB(NativeBannerAd nativeBannerAd, ViewGroup cardView) {
        nativeBannerAd.unregisterView();

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = null;
        view = inflater.inflate(R.layout.ads_nb_fb, null);
        cardView.addView(view);

        NativeAdLayout nativeAdLayout = view.findViewById(R.id.nativview);
        LinearLayout adChoicesContainer = view.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        TextView nativeAdTitle = view.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = view.findViewById(R.id.native_ad_social_context);
        MediaView nativeAdIconView = view.findViewById(R.id.native_icon_view);
        TextView nativeAdCallToAction = view.findViewById(R.id.b_name);
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdBodyText());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdIconView);
        clickableViews.add(nativeAdSocialContext);
        nativeBannerAd.registerViewForInteraction(view, nativeAdIconView, clickableViews);
    }

    /*public void inflate_NATIV_STARTAPP(final ArrayList<NativeAdDetails> ads, ViewGroup linearLayout) {
        try {

            LayoutInflater from = LayoutInflater.from(activity);
            View adView = from.inflate(R.layout.ads_nativ_startapp, null);

            linearLayout.removeAllViews();
            linearLayout.addView(adView);

            ImageView imgtop, imgbot, native_ad_icon;
            TextView native_ad_title, native_ad_body;
            Button native_ad_call_to_action;

            imgtop = adView.findViewById(R.id.imgtop);
            imgbot = adView.findViewById(R.id.imgbot);
            native_ad_icon = adView.findViewById(R.id.native_ad_icon);
            native_ad_title = adView.findViewById(R.id.native_ad_title);
            native_ad_body = adView.findViewById(R.id.native_ad_body);
            native_ad_call_to_action = adView.findViewById(R.id.native_ad_call_to_action);
            imgbot.setImageBitmap(ads.get(0).getImageBitmap());
            imgtop.setImageBitmap(ads.get(0).getImageBitmap());

            native_ad_icon.setImageBitmap(ads.get(0).getSecondaryImageBitmap());
            native_ad_title.setText(ads.get(0).getTitle());
            native_ad_body.setText(ads.get(0).getDescription());
            ads.get(0).registerViewForInteraction( native_ad_icon);
            ads.get(0).registerViewForInteraction( native_ad_title);
            ads.get(0).registerViewForInteraction( native_ad_body);
            ads.get(0).registerViewForInteraction( native_ad_call_to_action);
            ads.get(0).registerViewForInteraction( imgbot);
            ads.get(0).registerViewForInteraction( imgtop);


        } catch (Exception e) {

        }
    }*/


    public void inflate_NATIV_ADMOB(com.google.android.gms.ads.nativead.NativeAd nativeAd, ViewGroup cardView) {

        cardView.setVisibility(View.VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = (View) inflater
                .inflate(R.layout.ads_nativ_admob, null);
        cardView.removeAllViews();
        cardView.addView(view);

        NativeAdView adView = (NativeAdView) view.findViewById(R.id.uadview);


        adView.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }


        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);

    }

    public void inflate_NATIV_FB(NativeAd nativeAd, ViewGroup card) {

        card.setVisibility(View.VISIBLE);
        nativeAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        View adView = inflater.inflate(R.layout.ads_nativ_fb, null);

        card.removeAllViews();
        card.addView(adView);

        NativeAdLayout nativeAdLayout = adView.findViewById(R.id.nativview);

        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);

        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdBody);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdIcon);
        clickableViews.add(nativeAdSocialContext);

        nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }
}
