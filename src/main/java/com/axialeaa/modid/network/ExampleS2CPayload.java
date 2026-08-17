package com.axialeaa.modid.network;

import com.axialeaa.modid.ExampleMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record ExampleS2CPayload() implements CustomPacketPayload {

    public static final Type<ExampleS2CPayload> TYPE = ExampleMod.payloadType("example");
    public static final StreamCodec<FriendlyByteBuf, ExampleS2CPayload> STREAM_CODEC = StreamCodec.unit(new ExampleS2CPayload());

    @Override
    @NotNull
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
