package com.umldesigner.schema.foreign_key.fascade;


import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;


public interface SFKFascade {
    // NOTE if this has lots of methods (more than 5) fascade design pattern should
    // be fully implemented

    /**
     * @implSpec makes sure that the foreign key is valid
     * @param uuid id of the main item (the one that is doing the reference)
     * @param refUuid id of the secondary item (the one that is being referenced)
     * @param pojo  the pojo that is being checked
     * @return true if the given foreign key is valid
     * @see {@link #sameTableFKCheck(String, String)},
     *      {@link #validArgumentsCheck(SFKPojo)},
     */
    public boolean isValid(String uuid, String refUuid, SFKPojo pojo);

    /**
     * @implSpec checks whether the items are from the same table
     * 
     * @param uuid id of the main item (the one that is doing the reference)
     * @param refUuid id of the secondary item (the one that is being referenced)
     * @return true if the given items belong to the same table
     * @implNote this should be moved to a SItemFascade if other components have need
     *          of this
     */
    public boolean sameTableFKCheck(String uuid, String refUuid);

    /**
     * @implSpec checks whether the given given arguments about the foreign key are valid,
     * arguments like "OnDelete", "OnUpdate" identities not included
     * 
     * @param pojo input pojo
     * @return true if the arguments are valid
     */
    public boolean validArgumentsCheck(SFKPojo pojo);

}
