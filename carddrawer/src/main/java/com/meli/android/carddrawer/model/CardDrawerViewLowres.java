package com.meli.android.carddrawer.model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import com.meli.android.carddrawer.R;


public class CardDrawerViewLowres extends CardDrawerView {

    public CardDrawerViewLowres(@NonNull final Context context) {
        this(context, null);
    }

    public CardDrawerViewLowres(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardDrawerViewLowres(@NonNull final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    @LayoutRes
    protected int getLayout() {
        return R.layout.card_drawer_layout_lowres;
    }

    @Override
    protected void updateCardLogo(final ImageSwitcher cardLogoView, @NonNull final CardUI source,
        final boolean animate) {
        cardLogoView.setAnimateFirstView(animate);
        final ImageView cardImageView = (ImageView) cardLogoView.getNextView();
        //CardUI implementation can define the card logo in getCardLogoRes or setCardLogo method
        cardImageView.setImageResource(source.getCardLogoImageRes());
        source.setCardLogoImage(cardImageView);
        if (cardImageView.getDrawable() != null) {
            cardLogoView.setVisibility(VISIBLE);
        }
        cardLogoView.showNext();
    }

    @Override
    protected void updateIssuerLogo(final ImageSwitcher issuerLogoView, @NonNull final CardUI source,
        final boolean animate) {
        //No issuer logo on low res.
    }

    @Override
    protected void setupImageSwitcher(@Nullable final ImageSwitcher imageSwitcher, @NonNull final Animation fadeIn,
        @NonNull final Animation fadeOut) {
        //The issuer image switcher is null on low res
        if (imageSwitcher == null) {
            return;
        }
        super.setupImageSwitcher(imageSwitcher, fadeIn, fadeOut);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(final Animation animation) {
                //Nothing to do here
            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                if (((ImageView) imageSwitcher.getCurrentView()).getDrawable() == null) {
                    imageSwitcher.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationRepeat(final Animation animation) {
                //Nothing to do here
            }
        });
    }
}