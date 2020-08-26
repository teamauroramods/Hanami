package com.teamaurora.hanami.client;

import com.teamaurora.hanami.client.particle.HanamiParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.DistExecutor;

public class BlombParticleHandlerThing {
    public static class ParticleRunnableBullshit implements DistExecutor.SafeRunnable {
        final double x, y, z;
        final double xo, yo, zo;
        public ParticleRunnableBullshit(double xp, double yp, double zp, double xop, double yop, double zop) {
            x = xp;
            y = yp;
            z = zp;
            xo = xop;
            yo = yop;
            zo = zop;
        }
        public void run() {
            Minecraft.getInstance().world.addParticle(HanamiParticles.BLOSSOM_PETAL.get(), x, y, z, xo, yo, zo);
        }
    }
}
