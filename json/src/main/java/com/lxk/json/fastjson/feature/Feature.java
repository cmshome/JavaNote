package com.lxk.json.fastjson.feature;

/**
 * fast json 的配置信息的枚举
 *
 * @author wenshao[szujobs@hotmail.com]
 */
public enum Feature {
    /**
     *
     */
    AutoCloseSource,
    /**
     *
     */
    AllowComment,
    /**
     *
     */
    AllowUnQuotedFieldNames,
    /**
     *
     */
    AllowSingleQuotes,
    /**
     *
     */
    InternFieldNames,
    /**
     *
     */
    AllowISO8601DateFormat,

    /**
     * {"a":1,,,"b":2}
     */
    AllowArbitraryCommas,

    /**
     *
     */
    UseBigDecimal,

    /**
     * @since 1.1.2
     */
    IgnoreNotMatch,

    /**
     * 如果你用fastjson序列化的文本，输出的结果是按照fieldName排序输出的，parser时也能利用这个顺序进行优化读取。这种情况下，parser能够获得非常好的性能
     *
     * @since 1.1.3
     */
    SortFeidFastMatch,

    /**
     * 使用asm高效反射
     * @since 1.1.3
     */
    DisableASM,

    /**
     * 禁用循环引用检测
     *
     * @since 1.1.7
     */
    DisableCircularReferenceDetect,

    /**
     * @since 1.1.10
     */
    InitStringFieldAsEmpty,

    /**
     * @since 1.1.35
     */
    SupportArrayToBean,

    /**
     * @since 1.2.3
     */
    OrderedField,

    /**
     * @since 1.2.5
     */
    DisableSpecialKeyDetect,

    /**
     * @since 1.2.9
     */
    UseObjectArray,

    /**
     * @since 1.2.22, 1.1.54.android
     */
    SupportNonPublicField,

    /**
     * @since 1.2.29
     * <p>
     * disable autotype key '@type'
     */
    IgnoreAutoType,

    /**
     * @since 1.2.30
     * <p>
     * disable field smart match, improve performance in some scenarios.
     */
    DisableFieldSmartMatch,

    /**
     * @since 1.2.41, backport to 1.1.66.android
     */
    SupportAutoType,

    /**
     * @since 1.2.42
     */
    NonStringKeyAsString,

    /**
     * @since 1.2.45
     */
    CustomMapDeserializer,

    /**
     * @since 1.2.55
     */
    ErrorOnEnumNotMatch;

    Feature() {
        mask = (1 << ordinal());
    }

    public final int mask;

    public final int getMask() {
        return mask;
    }

    public static boolean isEnabled(int features, Feature feature) {
        return (features & feature.mask) != 0;
    }

    public static int config(int features, Feature feature, boolean state) {
        if (state) {
            features |= feature.mask;
        } else {
            features &= ~feature.mask;
        }

        return features;
    }

    public static int of(Feature[] features) {
        if (features == null) {
            return 0;
        }

        int value = 0;

        for (Feature feature : features) {
            value |= feature.mask;
        }

        return value;
    }
}
