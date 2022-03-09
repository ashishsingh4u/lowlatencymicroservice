package com.techienotes.lowlatencymicroservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@AllArgsConstructor
@NoArgsConstructor
public class PricingData implements Externalizable {
    public long id;
    public StringBuilder data;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(id);
        out.writeUTF(data.toString());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.id = in.readLong();
        if (this.data == null)
            this.data = new StringBuilder();
        data.append(in.readUTF());
    }
}
