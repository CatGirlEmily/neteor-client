/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.addons;

import neteordevelopment.neteorclient.NeteorClient;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;

import java.util.ArrayList;
import java.util.List;

public class AddonManager {
    public static final List<NeteorAddon> ADDONS = new ArrayList<>();

    public static void init() {
        // Neteor pseudo addon
        {
            NeteorClient.ADDON = new NeteorAddon() {
                @Override
                public void onInitialize() {}

                @Override
                public String getPackage() {
                    return "neteordevelopment.neteorclient";
                }

                @Override
                public String getWebsite() {
                    return "https://neteorclient.com";
                }

                @Override
                public GithubRepo getRepo() {
                    return new GithubRepo("NeteorDevelopment", "neteor-client");
                }

                @Override
                public String getCommit() {
                    String commit = NeteorClient.MOD_META.getCustomValue(NeteorClient.MOD_ID + ":commit").getAsString();
                    return commit.isEmpty() ? null : commit;
                }
            };

            ModMetadata metadata = FabricLoader.getInstance().getModContainer(NeteorClient.MOD_ID).get().getMetadata();

            NeteorClient.ADDON.name = metadata.getName();
            NeteorClient.ADDON.authors = new String[metadata.getAuthors().size()];
            if (metadata.containsCustomValue(NeteorClient.MOD_ID + ":color")) {
                NeteorClient.ADDON.color.parse(metadata.getCustomValue(NeteorClient.MOD_ID + ":color").getAsString());
            }

            int i = 0;
            for (Person author : metadata.getAuthors()) {
                NeteorClient.ADDON.authors[i++] = author.getName();
            }

            ADDONS.add(NeteorClient.ADDON);
        }

        // Addons
        for (EntrypointContainer<NeteorAddon> entrypoint : FabricLoader.getInstance().getEntrypointContainers("neteor", NeteorAddon.class)) {
            ModMetadata metadata = entrypoint.getProvider().getMetadata();
            NeteorAddon addon;
            try {
                addon = entrypoint.getEntrypoint();
            } catch (Throwable throwable) {
                throw new RuntimeException("Exception during addon init \"%s\".".formatted(metadata.getName()), throwable);
            }

            addon.name = metadata.getName();

            if (metadata.getAuthors().isEmpty()) throw new RuntimeException("Addon \"%s\" requires at least 1 author to be defined in it's fabric.mod.json. See https://fabricmc.net/wiki/documentation:fabric_mod_json_spec".formatted(addon.name));
            addon.authors = new String[metadata.getAuthors().size()];

            if (metadata.containsCustomValue(NeteorClient.MOD_ID + ":color")) {
                addon.color.parse(metadata.getCustomValue(NeteorClient.MOD_ID + ":color").getAsString());
            }

            int i = 0;
            for (Person author : metadata.getAuthors()) {
                addon.authors[i++] = author.getName();
            }

            ADDONS.add(addon);
        }
    }
}
