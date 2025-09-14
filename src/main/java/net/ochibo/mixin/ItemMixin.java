package net.ochibo.mixin;

import com.mojang.brigadier.Message;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow public abstract ComponentMap getComponents();

    @Inject(
            method = "appendTooltip",
            at = @At("HEAD")
    )
    public void foodLore(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type, CallbackInfo ci){
        if(this.getComponents().contains(DataComponentTypes.FOOD)){
            List<String> nutlist = new ArrayList<>();
            int nut = this.getComponents().get(DataComponentTypes.FOOD).nutrition();
            while (nut > 0){
                if (nut >= 2){
                    nutlist.addFirst("\uEFF0");
                    nut -= 2;
                }else{
                    nutlist.addFirst("\uEFF1");
                    nut -=1;
                }
            }
            float sat = this.getComponents().get(DataComponentTypes.FOOD).saturation();
            List<String> satlist = new ArrayList<>();
            while (sat > 0.0f){
                if (sat >= 2){
                    satlist.addFirst("\uEFF2");
                    sat -= 2.0f;
                }else if(sat >= 1.5f){
                    satlist.addFirst("\uEFF3");
                    sat -=2f;
                }else if(sat >= 1.0f){
                    satlist.addFirst("\uEFF4");
                    sat -=1.5f;
                }else{
                    satlist.addFirst("\uEFF5");
                    sat -=1.0f;
                }
            }
            textConsumer.accept(Text.of(String.join("",nutlist)));
            textConsumer.accept(Text.of(String.join("",satlist)));
        }
    }
}
