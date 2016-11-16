package com.mkmnav.android.parallelTaskStorage;

import android.os.Bundle;

import com.mkmnav.android.parallelTaskDefinitions.AT_HTMLDownloader;

/**
 * Created by chapa on 16-11-2016.
 */

public abstract class ParallelTaskStorage {

    public static AT_HTMLDownloader<Void,Bundle> htmlDownloader=null;

}
