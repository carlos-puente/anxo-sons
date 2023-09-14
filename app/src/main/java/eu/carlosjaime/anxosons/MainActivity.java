package eu.carlosjaime.anxosons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.Scanner;

import eu.carlosjaime.anxosons.definition.Constants;
import eu.carlosjaime.anxosons.definition.ItemType;
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
private String test = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        getData();
        setButtons();
    }

    private void getData() {
        try {
            InputStream inputStream = this.getAssets().open(Constants.DATA_JSON);
            String jsonString = new Scanner(inputStream).useDelimiter(Constants.JSON_DELIMITER).next();
            itemCollection = new Gson().fromJson(jsonString, ItemCollection.class);
        } catch (Exception ex){
            Toast.makeText(context, getString(R.string.error_loading_data)+ ex.getMessage(), Toast.LENGTH_LONG);
            itemCollection = new ItemCollection();
        }
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

    private void setPrevNextImages() {
        btnImgPrev.setImageDrawable(getPreviousDrawableBaseImage());
        btnImgNext.setImageDrawable(getNextDrawableBaseImage());
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
        return getDrawableFromId(itemCollection.getCurrentItem().getDrawableBaseImage());
    }

    private Drawable getNextDrawableBaseImage() {
        return getDrawableFromId(itemCollection.getNextItem().getDrawableBaseImage());
    }

    private Drawable getPreviousDrawableBaseImage() {
        return getDrawableFromId(itemCollection.getPreviousItem().getDrawableBaseImage());
    }

    private Drawable getDrawableActiveImage() {
        return getDrawableFromId(itemCollection.getCurrentItem().getDrawableActiveImage());
    }

    private Drawable getDrawableFromId(int id) {
        return getResources().getDrawable(id);
    }

}