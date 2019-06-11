package com.gamsys.services;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.util.Log;

import com.grv.utils.GattAttributes;

import java.util.UUID;

public class RedoxerDeviceService
{

    private BluetoothLeService mBluetoothLeService;
    private BluetoothGattCharacteristic mDeviceCommunicationCharacteristic;
    private BluetoothGattService mDeviceCommunicationService;
    private BluetoothGatt mBluetoothGatt;
    private final static String TAG = RedoxerDeviceService.class.getSimpleName();

    public RedoxerDeviceService(BluetoothLeService bluetoothLeService, BluetoothGatt bluetoothGatt){
        mBluetoothLeService= bluetoothLeService;
        mBluetoothGatt= bluetoothGatt;

        mDeviceCommunicationService = mBluetoothGatt.getService(UUID.fromString(GattAttributes.SERVICE_GENERIC_ACCESS));
        if (mDeviceCommunicationService != null) {
            mDeviceCommunicationCharacteristic = mDeviceCommunicationService
                    .getCharacteristic(UUID.fromString(GattAttributes.CHARACTERISTIC_GENERIC_ACCESS_WRITE));
            if (mDeviceCommunicationCharacteristic == null) {
                Log.e(TAG, "Communication characteristic not discovered in the device!");
            }
        }else{
            Log.e(TAG, "Communication service not discovered in the device!");
        }
    }

     /**
     * Start Device
     * @return If start command send successfully
     */
    public boolean startDevice() throws Exception
    {
        byte[] command= new byte[]{(byte)0xBE, (byte)0xB0, 0x01, (byte)0xc0, 0x36};

        mBluetoothLeService.setCharacteristicNotification(mDeviceCommunicationCharacteristic, true);

        return  mBluetoothLeService.writeCharacteristic(mDeviceCommunicationCharacteristic,command);
       // return mBluetoothLeService.writeCharacteristic(mDeviceCommunicationCharacteristic, command);
    }
}
