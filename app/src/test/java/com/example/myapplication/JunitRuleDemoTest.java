package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JunitRuleDemoTest {
    @Rule
    public final TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void countsAssets() throws IOException {
        File icon = tempFolder.newFile("icon.png");
        File assets = tempFolder.newFolder("assets");

        DigitalAssetManager dam = new DigitalAssetManager(icon, assets);
        assertEquals(1, dam.getAssetCount());
    }


    @Test
    public void throwsIllegalArgumentExceptionIfIconIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Icon is null, not a file, or doesn't exist.");
        new DigitalAssetManager(null, null);
    }


    public static class DigitalAssetManager {

        private final List<File> assetList = new ArrayList<>();
        public DigitalAssetManager(File icon, File assets) {
            if (icon == null) {
                throw new IllegalArgumentException("Icon is null, not a file, or doesn't exist.");
            }
            assetList.add(assets);
        }


        public int getAssetCount() {
            return assetList.size();
        }
    }
}
