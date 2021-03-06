/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.pump.db;

import android.net.Uri;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AnyThread
public class Album {
    private final long mId;

    // TODO(b/123706949) Lock mutable fields to ensure consistent updates
    private String mTitle;
    private String mDescription;
    private Uri mAlbumArtUri;
    private Artist mArtist;
    private final List<Audio> mAudios = new ArrayList<>();
    private boolean mLoaded;

    Album(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public @Nullable String getTitle() {
        return mTitle;
    }

    public @Nullable Uri getAlbumArtUri() {
        return mAlbumArtUri;
    }

    public @Nullable Artist getArtist() {
        return mArtist;
    }

    public @NonNull List<Audio> getAudios() {
        return Collections.unmodifiableList(mAudios);
    }

    public @Nullable String getDescription() {
        return mDescription;
    }

    public boolean setAlbumArtUri(@NonNull Uri albumArtUri) {
        if (albumArtUri.equals(mAlbumArtUri)) {
            return false;
        }
        mAlbumArtUri = albumArtUri;
        return true;
    }

    public boolean setDescription(@NonNull String description) {
        if (description.equals(mDescription)) {
            return false;
        }
        mDescription = description;
        return true;
    }

    boolean setTitle(@NonNull String title) {
        if (title.equals(mTitle)) {
            return false;
        }
        mTitle = title;
        return true;
    }

    boolean setArtist(@NonNull Artist artist) {
        if (artist.equals(mArtist)) {
            return false;
        }
        mArtist = artist;
        return true;
    }

    boolean addAudio(@NonNull Audio audio) {
        if (mAudios.contains(audio)) {
            return false;
        }
        return mAudios.add(audio);
    }

    boolean isLoaded() {
        return mLoaded;
    }

    void setLoaded() {
        mLoaded = true;
    }

    @Override
    public final boolean equals(@Nullable Object obj) {
        return obj instanceof Album && mId == ((Album) obj).mId;
    }

    @Override
    public final int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }
}
