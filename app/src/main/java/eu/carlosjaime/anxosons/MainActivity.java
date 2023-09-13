package eu.carlosjaime.anxosons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import eu.carlosjaime.anxosons.definition.ItemType;
import eu.carlosjaime.anxosons.helper.Helper;
import eu.carlosjaime.anxosons.objects.ItemCollection;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnActiveItem;
    private ImageButton btnMenuBells;
    private ImageButton btnMenuAnimals;
    private ImageButton btnMenuTransports;
    private ImageButton btnMenuInstruments;
    private ImageButton btnImgNext;
    private ImageButton btnImgPrev;
    private Context context = this;
    private MediaPlayer mediaPlayer;
    private ItemCollection itemCollection = new ItemCollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        Helper.fillItems(itemCollection);
        setButtons();
    }

    private void setButtons() {
        setActiveItemButton();
        setPreviousButton();
        setNextButton();
        setMenuButtons();
    }

    private void setMenuButtons() {
        setMenuBells();
        setMenuAnimals();
        setMenuTransports();
        setMenuInstruments();
    }

    private void setMenuInstruments() {
        btnMenuInstruments = findViewById(R.id.btnMenuInstruments);
        btnMenuInstruments.setOnClickListener(view -> {
            resetActiveItem(ItemType.INSTRUMENTS);
        });
    }

    private void setMenuTransports() {
        btnMenuTransports = findViewById(R.id.btnMenuTransport);
        btnMenuTransports.setOnClickListener(view -> {
            resetActiveItem(ItemType.TRANSPORTS);
        });
    }

    private void setMenuAnimals() {
        btnMenuAnimals = findViewById(R.id.btnMenuAnimals);
        btnMenuAnimals.setOnClickListener(view -> {
            resetActiveItem(ItemType.ANIMALS);
        });
    }

    private void setMenuBells() {
        btnMenuBells = findViewById(R.id.btnMenuBells);
        btnMenuBells.setOnClickListener(view -> {
            resetActiveItem(ItemType.BELLS);
        });
    }

    private void resetActiveItem(ItemType type) {
        itemCollection.setActive(false);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        itemCollection.setCurrentPosition(0);
        itemCollection.setActiveType(type);
        setActiveItemButton();
        setPrevNextImages();
    }

    private void setNextButton() {
        btnImgNext = findViewById(R.id.btnImgNext);
        btnImgNext.setImageDrawable(getNextDrawableBaseImage());
        btnImgNext.setOnClickListener(view -> {
            itemCollection.setActive(false);
            itemCollection.setCurrentPosition(itemCollection.getCurrentPosition() + 1);
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            btnActiveItem.setImageDrawable(getDrawableBaseImage());
            setPrevNextImages();
        });
    }

    private void setPrevNextImages() {
        btnImgPrev.setImageDrawable(getPreviousDrawableBaseImage());
        btnImgNext.setImageDrawable(getNextDrawableBaseImage());
    }

    private void setPreviousButton() {
        btnImgPrev = findViewById(R.id.btnImgPrev);
        btnImgPrev.setImageDrawable(getPreviousDrawableBaseImage());
        btnImgPrev.setOnClickListener(view -> {
            itemCollection.setActive(false);
            itemCollection.setCurrentPosition(itemCollection.getCurrentPosition() - 1);
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            btnActiveItem.setImageDrawable(getDrawableBaseImage());
            setPrevNextImages();
        });
    }

    private void setActiveItemButton() {
        btnActiveItem = findViewById(R.id.btnImgSound);
        btnActiveItem.setImageDrawable(getDrawableBaseImage());
        btnActiveItem.setOnClickListener(view -> {
            if (!itemCollection.isActive()) {
                itemCollection.setActive(true);
                btnActiveItem.setImageDrawable(
                        getDrawableActiveImage()
                );
            }
            generateMediaPlayer();
        });
    }

    private void generateMediaPlayer() {
        mediaPlayer = MediaPlayer.create(context, itemCollection.getCurrentItem().getRawSound());
        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            itemCollection.setActive(false);
            btnActiveItem.setImageDrawable(getDrawableBaseImage());
        });
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(context, itemCollection.getCurrentItem().getRawSound());
        }
        mediaPlayer.start();
    }

    private Drawable getDrawableBaseImage() {
        return getResources().getDrawable(itemCollection.getCurrentItem().getDrawableBaseImage());
    }

    private Drawable getNextDrawableBaseImage() {
        return getResources().getDrawable(itemCollection.getNextItem().getDrawableBaseImage());
    }

    private Drawable getPreviousDrawableBaseImage() {
        return getResources().getDrawable(itemCollection.getPreviousItem().getDrawableBaseImage());
    }

    private Drawable getDrawableActiveImage() {
        return getResources().getDrawable(itemCollection.getCurrentItem().getDrawableActiveImage());
    }

}