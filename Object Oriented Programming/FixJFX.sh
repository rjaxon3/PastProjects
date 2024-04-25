for file in $1/lib/*.dylib
do
    xattr -d com.apple.quarantine $file
done
