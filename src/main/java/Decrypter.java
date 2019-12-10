import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

public class Decrypter {

    private SecretKeySpec key = null;
    private byte[] keyAsLong = new byte[8];
    private byte[] desKey = new byte[8];

    public String decrypt ( SealedObject encryptedPassword ) {
        try {
            return (String)encryptedPassword.getObject(key);
        }catch( Exception e ) {}
        return null;
    }

    private static void formatAsDES(byte[] in, byte[] out) {
        out[0] = (byte) ((in[0] >> 1) & 0xff);
        out[1] = (byte) ((((in[0] & 0x01) << 6) | (((in[1] & 0xff)>>2) & 0xff)) & 0xff);
        out[2] = (byte) ((((in[1] & 0x03) << 5) | (((in[2] & 0xff)>>3) & 0xff)) & 0xff);
        out[3] = (byte) ((((in[2] & 0x07) << 4) | (((in[3] & 0xff)>>4) & 0xff)) & 0xff);
        out[4] = (byte) ((((in[3] & 0x0F) << 3) | (((in[4] & 0xff)>>5) & 0xff)) & 0xff);
        out[5] = (byte) ((((in[4] & 0x1F) << 2) | (((in[5] & 0xff)>>6) & 0xff)) & 0xff);
        out[6] = (byte) ((((in[5] & 0x3F) << 1) | (((in[6] & 0xff)>>7) & 0xff)) & 0xff);
        out[7] = (byte) (in[6] & 0x7F);

        for (int i = 0; i < 8; i++) {
            out[i] = (byte) (out[i] << 1);
        }
    }

    public void setKey ( long newKey ) {
        try {
            keyAsLong[0] = (byte) (newKey & 0xFF);
            keyAsLong[1] = (byte) ((newKey >> 8) & 0xFF);
            keyAsLong[2] = (byte) ((newKey >> 16) & 0xFF);
            keyAsLong[3] = (byte) ((newKey >> 24) & 0xFF);
            keyAsLong[4] = (byte) ((newKey >> 32) & 0xFF);
            keyAsLong[5] = (byte) ((newKey >> 40) & 0xFF);
            keyAsLong[6] = (byte) ((newKey >> 48) & 0xFF);
            keyAsLong[7] = (byte) ((newKey >> 56) & 0xFF);
            formatAsDES(keyAsLong, desKey);
            key = new SecretKeySpec(desKey, "DES");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public SealedObject[] encryptedArray(String plainPassword, int copies ){
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key );
            SealedObject[] arr = new SealedObject[copies];
            for(int i = 0; i < copies; i++) {
                arr[i] = new SealedObject( plainPassword, cipher);
            }
            return arr;
        } catch ( Exception e )	{
            System.out.println(e.getMessage()) ;
        }
        return null;
    }
}
