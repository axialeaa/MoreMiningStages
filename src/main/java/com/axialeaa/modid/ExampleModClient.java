package com.axialeaa.modid;

import com.axialeaa.modid.network.ExampleS2CPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ExampleModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ExampleS2CPayload.TYPE, (_, _) -> ExampleMod.meow());
    }

}
