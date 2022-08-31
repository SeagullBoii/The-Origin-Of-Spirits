package net.seagullboi.originofspirits.registry;

import com.google.common.collect.Lists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public enum TOOSTextFormatting {
    PINK("PINK", 'p', 0, 0),
    RESET("RESET", 'z', -1, (Integer)null);

    private static final Map<String, TOOSTextFormatting> NAME_MAPPING = Arrays.stream(values()).collect(Collectors.toMap((p_199746_0_) -> {
        return lowercaseAlpha(p_199746_0_.name);
    }, (p_199747_0_) -> {
        return p_199747_0_;
    }));
    private static final Pattern FORMATTING_CODE_PATTERN = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");
    /** The name of this color/formatting */
    private final String name;
    private final char formattingCode;
    private final boolean fancyStyling;
    private final String controlString;
    /** The numerical index that represents this color */
    private final int colorIndex;
    @Nullable
    private final Integer color;

    private static String lowercaseAlpha(String string) {
        return string.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
    }

    private TOOSTextFormatting(String formattingName, char formattingCodeIn, int index, @Nullable Integer colorCode) {
        this(formattingName, formattingCodeIn, false, index, colorCode);
    }

    private TOOSTextFormatting(String formattingName, char formattingCodeIn, boolean fancyStylingIn) {
        this(formattingName, formattingCodeIn, fancyStylingIn, -1, (Integer)null);
    }

    private TOOSTextFormatting(String formattingName, char formattingCodeIn, boolean fancyStylingIn, int index, @Nullable Integer colorCode) {
        this.name = formattingName;
        this.formattingCode = formattingCodeIn;
        this.fancyStyling = fancyStylingIn;
        this.colorIndex = index;
        this.color = colorCode;
        this.controlString = "\u00a7" + formattingCodeIn;
    }

    /**
     * Returns the numerical color index that represents this formatting
     */
    public int getColorIndex() {
        return this.colorIndex;
    }

    /**
     * False if this is just changing the color or resetting; true otherwise.
     */
    public boolean isFancyStyling() {
        return this.fancyStyling;
    }

    /**
     * Checks if this is a color code.
     */
    public boolean isColor() {
        return !this.fancyStyling && this != RESET;
    }

    @Nullable
    public Integer getColor() {
        return this.color;
    }

    /**
     * Gets the friendly name of this value.
     */
    public String getFriendlyName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String toString() {
        return this.controlString;
    }

    /**
     * Returns a copy of the given string, with formatting codes stripped away.
     */
    @Nullable
    public static String getTextWithoutFormattingCodes(@Nullable String text) {
        return text == null ? null : FORMATTING_CODE_PATTERN.matcher(text).replaceAll("");
    }

    /**
     * Gets a value by its friendly name; null if the given name does not map to a defined value.
     */
    @Nullable
    public static TOOSTextFormatting getValueByName(@Nullable String friendlyName) {
        return friendlyName == null ? null : NAME_MAPPING.get(lowercaseAlpha(friendlyName));
    }

    /**
     * Get a TOOSTextFormatting from it's color index
     */
    @Nullable
    public static TOOSTextFormatting fromColorIndex(int index) {
        if (index < 0) {
            return RESET;
        } else {
            for(TOOSTextFormatting textformatting : values()) {
                if (textformatting.getColorIndex() == index) {
                    return textformatting;
                }
            }

            return null;
        }
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public static TOOSTextFormatting fromFormattingCode(char formattingCodeIn) {
        char c0 = Character.toString(formattingCodeIn).toLowerCase(Locale.ROOT).charAt(0);

        for(TOOSTextFormatting textformatting : values()) {
            if (textformatting.formattingCode == c0) {
                return textformatting;
            }
        }

        return null;
    }

    /**
     * Gets all the valid values.
     */
    public static Collection<String> getValidValues(boolean getColor, boolean getFancyStyling) {
        List<String> list = Lists.newArrayList();

        for(TOOSTextFormatting textformatting : values()) {
            if ((!textformatting.isColor() || getColor) && (!textformatting.isFancyStyling() || getFancyStyling)) {
                list.add(textformatting.getFriendlyName());
            }
        }

        return list;
    }
}
